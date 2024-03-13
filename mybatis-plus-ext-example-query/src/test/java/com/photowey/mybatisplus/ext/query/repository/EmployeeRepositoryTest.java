package com.photowey.mybatisplus.ext.query.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.photowey.mybatisplus.ext.annotation.*;
import com.photowey.mybatisplus.ext.core.domain.operator.Operator;
import com.photowey.mybatisplus.ext.enmus.CompareEnum;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import com.photowey.mybatisplus.ext.query.QueryWrapperExt;
import com.photowey.mybatisplus.ext.query.domain.entity.Employee;
import com.photowey.mybatisplus.ext.validator.ValueValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

/**
 * {@code EmployeeRepositoryTest}
 *
 * @author weichangjun
 * @date 2022/02/18
 * @since 1.0.0
 */
@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testHello() {
        Employee employee = this.employeeRepository.selectById(0L);
        Assertions.assertNull(employee);
    }

    @Test
    void testQuery1() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setId(1L);
        employeeQuery.setEmployeeNo("2021109527");
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusDays(2);
        employeeQuery.setCreateTimeFrom(from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        employeeQuery.setCreateTimeTo(to.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        // 通常的写法1 - 会很冗长
        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getId())) {
            queryWrapper.eq("id", employeeQuery.getId());
        }

        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getEmployeeNo())) {
            queryWrapper.eq("employee_no", employeeQuery.getEmployeeNo());
        }

        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getOrgName())) {
            queryWrapper.likeLeft("org_name", employeeQuery.getOrgName());
        }

        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getRemark())) {
            queryWrapper.likeRight("org_name", employeeQuery.getRemark());
        }

        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getOrderNo())) {
            queryWrapper.ge("order_no", employeeQuery.getOrderNo());
        }

        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getStatus())) {
            queryWrapper.le("status", employeeQuery.getStatus());
        }

        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getCreateTimeFrom())) {
            queryWrapper.ge("update_time", employeeQuery.getCreateTimeFrom());
        }

        if (ValueValidator.isNotNullOrEmpty(employeeQuery.getCreateTimeTo())) {
            queryWrapper.le("update_time", employeeQuery.getCreateTimeTo());
        }

        List<Employee> employees = this.employeeRepository.selectList(queryWrapper);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testQuery2() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setId(1L);
        employeeQuery.setEmployeeNo("2021109527");
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusDays(2);
        employeeQuery.setCreateTimeFrom(from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        employeeQuery.setCreateTimeTo(to.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        // 通常的写法2 - 稍显很冗长
        queryWrapper.eq(ValueValidator.isNotNullOrEmpty(employeeQuery.getId()), "id", employeeQuery.getId());
        queryWrapper.eq(ValueValidator.isNotNullOrEmpty(employeeQuery.getEmployeeNo()), "employee_no", employeeQuery.getEmployeeNo());
        queryWrapper.likeLeft(ValueValidator.isNotNullOrEmpty(employeeQuery.getOrgName()), "org_name", employeeQuery.getOrgName());
        queryWrapper.likeRight(ValueValidator.isNotNullOrEmpty(employeeQuery.getRemark()), "org_name", employeeQuery.getRemark());

        queryWrapper.ge(ValueValidator.isNotNullOrEmpty(employeeQuery.getOrderNo()), "order_no", employeeQuery.getOrderNo());
        queryWrapper.le(ValueValidator.isNotNullOrEmpty(employeeQuery.getStatus()), "status", employeeQuery.getStatus());
        queryWrapper.ge(ValueValidator.isNotNullOrEmpty(employeeQuery.getCreateTimeFrom()), "update_time", employeeQuery.getCreateTimeFrom());
        queryWrapper.le(ValueValidator.isNotNullOrEmpty(employeeQuery.getCreateTimeTo()), "update_time", employeeQuery.getCreateTimeTo());

        List<Employee> employees = this.employeeRepository.selectList(queryWrapper);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testQueryExt() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setId(1L);
        employeeQuery.setEmployeeNo("2021109527");
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusDays(2);
        employeeQuery.setCreateTimeFrom(from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        employeeQuery.setCreateTimeTo(to.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        QueryWrapperExt<Employee> queryWrapper = new QueryWrapperExt<>();

        // 扩展写法 - 简洁很多
        queryWrapper.eqIfPresent("id", employeeQuery.getId());
        queryWrapper.eqIfPresent("employee_no", employeeQuery.getEmployeeNo());
        queryWrapper.likeIfPresent("org_name", employeeQuery.getOrgName());
        queryWrapper.likeIfPresent("org_name", employeeQuery.getRemark(), SqlLike.RIGHT);
        queryWrapper.geIfPresent("order_no", employeeQuery.getOrderNo());
        queryWrapper.leIfPresent("status", employeeQuery.getStatus());
        queryWrapper.geIfPresent("update_time", employeeQuery.getCreateTimeFrom());
        queryWrapper.leIfPresent("update_time", employeeQuery.getCreateTimeTo());

        queryWrapper.betweenIfPresent("update_time", employeeQuery.getCreateTimeTo(), employeeQuery.getCreateTimeTo());

        List<Employee> employees = this.employeeRepository.selectList(queryWrapper);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectAnnotation() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setId(1L);
        employeeQuery.setEmployeeNo("2021109527");
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusDays(2);
        employeeQuery.setCreateTimeFrom(from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        employeeQuery.setCreateTimeTo(to.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        String sqlSelect = employeeQuery.autoWrapperExt().getSqlSelect();

        Assertions.assertEquals("id,employee_no", sqlSelect);
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EmployeeQuery extends AbstractQuery<Employee> {

        @Eq
        private Long id;

        @Eq(alias = "employee_no")
        private String employeeNo;

        @Like(alias = "org_name", like = SqlLike.LEFT)
        private String orgName;

        @Ge(alias = "order_no")
        private Integer orderNo;

        @Le
        private Integer status;

        @Like(alias = "remark", like = SqlLike.RIGHT)
        private String remark;

        @Timestamp(alias = "update_time", compare = CompareEnum.GE, clazz = LocalDateTime.class)
        private Long createTimeFrom;

        @Timestamp(alias = "update_time", compare = CompareEnum.LE, clazz = LocalDateTime.class)
        private Long createTimeTo;

        @Select(value = {"id", "employee_no"})
        private String properties;
    }

    private Employee populateEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeNo("2021109527");
        employee.setOrgId(2021109527L);
        employee.setOrgName("宇宙漫游者");
        employee.setOrderNo(1024);
        employee.setStatus(1);
        employee.setRemark("我是备注");

        Operator operator = Operator.builder()
                .operatorId(20211095278848L)
                .build();

        LocalDateTime now = LocalDateTime.now();
        employee.setCreateBy(operator.getOperatorId());
        employee.setCreateTime(now);
        employee.setUpdateBy(operator.getOperatorId());
        employee.setUpdateTime(now);
        employee.setDeleted(0);

        return employee;
    }
}