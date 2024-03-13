package com.photowey.mybatisplus.ext.condition.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.photowey.mybatisplus.ext.annotation.*;
import com.photowey.mybatisplus.ext.condition.domain.entity.Employee;
import com.photowey.mybatisplus.ext.enmus.CompareEnum;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import com.photowey.mybatisplus.ext.query.LambdaQueryWrapperExt;
import com.photowey.mybatisplus.ext.query.QueryWrapperExt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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
    void testQueryExtEq() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setId(1L);

        QueryWrapperExt<Employee> employeeQueryWrapperExt = employeeQuery.autoWrapperExt();
        String targetSql = employeeQueryWrapperExt.getTargetSql();
        Assertions.assertEquals("(id = ?)", targetSql);
    }

    @Test
    void testQueryExtLike() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setEmployeeNo("2021109527");
        employeeQuery.setOrgName("宇宙漫游者");

        QueryWrapperExt<Employee> employeeQueryWrapperExt = employeeQuery.autoWrapperExt();
        String targetSql = employeeQueryWrapperExt.getTargetSql();
        Assertions.assertEquals("(employee_no = ? AND org_name LIKE ?)", targetSql);

        Employee employee = this.employeeRepository.selectOne(employeeQueryWrapperExt);
        // 关注执行的SQL
        // Preparing: SELECT ID,EMPLOYEE_NO,ORG_ID,ORG_NAME,ORDER_NO,STATUS,REMARK,create_time AS createTime,update_time AS updateTime,CREATE_BY,UPDATE_BY,DELETED FROM sys_employee WHERE DELETED=0 AND (employee_no = ? AND org_name LIKE ?)
        // Parameters: 2021109527(String), 宇宙漫游者%(String)
        Assertions.assertNull(employee);
    }

    @Test
    void testQueryExtGe() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setOrderNo(1024);

        QueryWrapperExt<Employee> employeeQueryWrapperExt = employeeQuery.autoWrapperExt();
        String targetSql = employeeQueryWrapperExt.getTargetSql();
        Assertions.assertEquals("(order_no >= ?)", targetSql);

        Employee employee = this.employeeRepository.selectOne(employeeQueryWrapperExt);
        // 关注执行的SQL
        // Preparing: SELECT ID,EMPLOYEE_NO,ORG_ID,ORG_NAME,ORDER_NO,STATUS,REMARK,create_time AS createTime,update_time AS updateTime,CREATE_BY,UPDATE_BY,DELETED FROM sys_employee WHERE DELETED=0 AND (order_no >= ?)
        // Parameters: 1024(Integer)
        Assertions.assertNull(employee);
    }

    @Test
    void testQueryExtLe() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setStatus(2);

        QueryWrapperExt<Employee> employeeQueryWrapperExt = employeeQuery.autoWrapperExt();
        String targetSql = employeeQueryWrapperExt.getTargetSql();
        Assertions.assertEquals("(status <= ?)", targetSql);

        Employee employee = this.employeeRepository.selectOne(employeeQueryWrapperExt);
        // 关注执行的SQL
        // Preparing: SELECT ID,EMPLOYEE_NO,ORG_ID,ORG_NAME,ORDER_NO,STATUS,REMARK,create_time AS createTime,update_time AS updateTime,CREATE_BY,UPDATE_BY,DELETED FROM sys_employee WHERE DELETED=0 AND (status <= ?)
        // Parameters: 2(Integer)
        Assertions.assertNull(employee);
    }

    @Test
    void testQueryExtTimeStamp() {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusDays(2);
        employeeQuery.setCreateTimeFrom(from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        employeeQuery.setCreateTimeTo(to.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        QueryWrapperExt<Employee> employeeQueryWrapperExt = employeeQuery.autoWrapperExt();
        String targetSql = employeeQueryWrapperExt.getTargetSql();
        Assertions.assertEquals("(create_time >= ? AND create_time <= ?)", targetSql);

        Employee employee = this.employeeRepository.selectOne(employeeQueryWrapperExt);
        // 关注执行的SQL
        // Preparing: Preparing: SELECT ID,EMPLOYEE_NO,ORG_ID,ORG_NAME,ORDER_NO,STATUS,REMARK,create_time AS createTime,update_time AS updateTime,CREATE_BY,UPDATE_BY,DELETED FROM sys_employee WHERE DELETED=0 AND (create_time >= ? AND create_time <= ?)
        // Parameters: Parameters: 2022-02-16 12:07:02.243(Timestamp), 2022-02-18 12:07:02.243(Timestamp)
        Assertions.assertNull(employee);
    }

    @Test
    void testAnd() {
        EmployeeConditionQuery employeeQuery = new EmployeeConditionQuery();
        employeeQuery.setOrgName("宇宙漫游者");
        employeeQuery.setStatus(2);
        QueryWrapper<Employee> queryWrapper = employeeQuery.autoWrapper();
        Employee employee = this.employeeRepository.selectOne(queryWrapper);

        // 关注执行的SQL
        // Preparing: ... WHERE DELETED=0 AND ((org_name = ? OR org_name LIKE ?) OR (status = ? OR remark = ?))
        // Parameters: 宇宙漫游者(String), 宇宙漫游者%(String), 1(Integer), walk(String)
        Assertions.assertNull(employee);
    }

    @Test
    void testOr() {
        // 注意顺序
        EmployeeConditionQuery2 employeeQuery = new EmployeeConditionQuery2();
        employeeQuery.setOrgName("宇宙漫游者");
        employeeQuery.setStatus(2);
        QueryWrapper<Employee> queryWrapper = employeeQuery.autoWrapper();
        Employee employee = this.employeeRepository.selectOne(queryWrapper);

        // 关注执行的SQL
        // Preparing: ... WHERE DELETED=0 AND ((status = ? OR remark = ?) AND (org_name = ? OR org_name LIKE ?))
        // Parameters: Parameters: 1(Integer), walk(String), 宇宙漫游者(String), 宇宙漫游者%(String)
        Assertions.assertNull(employee);
    }

    @Test
    void testOrderByAndLimit() {
        LambdaQueryWrapperExt<Employee> wrapper = new LambdaQueryWrapperExt<Employee>()
                .selector(Employee::getId, Employee::getEmployeeNo)
                .eq(Employee::getOrgId, 9527L)
                .orderByDesc(Employee::getId)
                .limit(1);

        Employee employee = this.employeeRepository.selectOne(wrapper);
        String targetSql = wrapper.getTargetSql();

        // SELECT id,employee_no FROM sys_employee WHERE deleted=0 AND (org_id = ?) ORDER BY id DESC LIMIT 1
        Assertions.assertNull(employee);
        Assertions.assertEquals("(org_id = ?) ORDER BY id DESC LIMIT 1", targetSql);
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EmployeeQuery extends AbstractQuery<Employee> {

        @Eq
        private Long id;

        @Eq(alias = "employee_no")
        private String employeeNo;

        @Like(alias = "org_name", like = SqlLike.RIGHT)
        private String orgName;

        @Ge(alias = "order_no")
        private Integer orderNo;

        @Le
        private Integer status;

        @Timestamp(alias = "create_time", compare = CompareEnum.GE, clazz = LocalDateTime.class)
        private Long createTimeFrom;

        @Timestamp(alias = "create_time", compare = CompareEnum.LE, clazz = LocalDateTime.class)
        private Long createTimeTo;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EmployeeConditionQuery extends AbstractQuery<Employee> {

        @And(handler = "employeeConditionHandler")
        private String orgName;

        @Or(handler = "employeeConditionHandler")
        private Integer status;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EmployeeConditionQuery2 extends AbstractQuery<Employee> {

        // 注意和 EmployeeConditionQuery 区分
        @Or(handler = "employeeConditionHandler")
        private Integer status;

        @And(handler = "employeeConditionHandler")
        private String orgName;
    }
}