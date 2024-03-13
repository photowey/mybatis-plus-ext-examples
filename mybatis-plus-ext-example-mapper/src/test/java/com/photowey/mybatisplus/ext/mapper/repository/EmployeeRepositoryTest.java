package com.photowey.mybatisplus.ext.mapper.repository;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.photowey.mybatisplus.ext.annotation.*;
import com.photowey.mybatisplus.ext.condition.domain.entity.Employee;
import com.photowey.mybatisplus.ext.core.domain.operator.Operator;
import com.photowey.mybatisplus.ext.enmus.CompareEnum;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    void testSelectOne() {
        Employee employee = this.employeeRepository.selectOne("id", 0L);
        Assertions.assertNull(employee);
    }

    @Test
    void testSelectOneCallback() {
        Employee employee = this.employeeRepository.selectOne("id", 0L, (wrapper) -> {
            wrapper.eq("employee_no", "2021109527");
        });
        Assertions.assertNull(employee);
    }

    @Test
    void testSelectOneFunction() {
        Employee employee = this.employeeRepository.selectOne(Employee::getId, 0L);
        Assertions.assertNull(employee);
    }

    @Test
    void testSelectOneFunctionCallback() {
        Employee employee = this.employeeRepository.selectOne(Employee::getId, 0L, (wrapper) -> {
            wrapper.eq(Employee::getEmployeeNo, "2021109527");
        });
        Assertions.assertNull(employee);
    }

    @Test
    void testSelectListEmpty() {
        List<Employee> employees = this.employeeRepository.selectList();
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectList() {
        List<Employee> employees = this.employeeRepository.selectList("id", 0L);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListCallback() {
        List<Employee> employees = this.employeeRepository.selectList("id", 0L, (wrapper) -> {
            wrapper.eq("employee_no", "2021109527");
        });
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListFunction() {
        List<Employee> employees = this.employeeRepository.selectList(Employee::getId, 0L);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListFunctionCallback() {
        List<Employee> employees = this.employeeRepository.selectList(Employee::getId, 0L, (wrapper) -> {
            wrapper.eq(Employee::getEmployeeNo, "2021109527");
        });
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListIn() {
        List<Employee> employees = this.employeeRepository.selectList(Employee::getId, this.populateIds());
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListEmptyFalse() {
        List<Employee> employees = this.employeeRepository.selectList("id", new ArrayList<>(), false);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListEmptyTrue() {
        List<Employee> employees = this.employeeRepository.selectList("id", new ArrayList<>(), true);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListFalse() {
        List<Employee> employees = this.employeeRepository.selectList("id", this.populateIds(), false);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListTrue() {
        List<Employee> employees = this.employeeRepository.selectList("id", this.populateIds(), true);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListEmptyFalseFunction() {
        List<Employee> employees = this.employeeRepository.selectList(Employee::getId, new ArrayList<>(), false);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListEmptyTrueFunction() {
        List<Employee> employees = this.employeeRepository.selectList(Employee::getId, new ArrayList<>(), true);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListFalseFunction() {
        List<Employee> employees = this.employeeRepository.selectList(Employee::getId, this.populateIds(), false);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectListTrueFunction() {
        List<Employee> employees = this.employeeRepository.selectList(Employee::getId, this.populateIds(), true);
        Assertions.assertEquals(0, employees.size());
    }

    @Test
    void testSelectCount() {
        long count = this.employeeRepository.selectCount("id", 0L);
        Assertions.assertEquals(0, count);
    }

    @Test
    void testSelectCountCallback() {
        long count = this.employeeRepository.selectCount("id", 0L, (wrapper) -> {
            wrapper.eq("employee_no", "2021109527");
        });
        Assertions.assertEquals(0, count);
    }

    @Test
    void testSelectCountFunction() {
        long count = this.employeeRepository.selectCount(Employee::getId, 0L);
        Assertions.assertEquals(0, count);
    }

    @Test
    void testSelectCountFunctionCallback() {
        long count = this.employeeRepository.selectCount(Employee::getId, 0L, (wrapper) -> {
            wrapper.eq(Employee::getEmployeeNo, "2021109527");
        });
        Assertions.assertEquals(0, count);
    }

    /**
     * 添加配置
     * <pre>
     * mybatis-plus:
     *   ext:
     *     db-type: H2
     *     overflow: true
     *     use-deprecated-executor: false
     * </pre>
     */
    @Test
    void testSelectPage() {
        Long id = 1494556614122921985L;
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setId(id);
        employeeQuery.setEmployeeNo("2021109527");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.minusDays(2);
        employeeQuery.setCreateTimeFrom(from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        employeeQuery.setCreateTimeTo(now.plusDays(1).toInstant(ZoneOffset.of("+8")).toEpochMilli());

        Employee employee = this.populateEmployee();
        employee.setId(id);
        this.employeeRepository.insert(employee);

        // Preparing: SELECT ID,EMPLOYEE_NO,ORG_ID,ORG_NAME,ORDER_NO,STATUS,REMARK,update_time AS createTime,update_time AS updateTime,CREATE_BY,UPDATE_BY,DELETED FROM sys_employee WHERE DELETED=0 AND (id = ? AND employee_no = ? AND update_time >= ? AND update_time <= ?) LIMIT ?
        // Parameters: 1494556614122921985(Long), 2021109527(String), 2022-02-16 15:02:24.521(Timestamp), 2022-02-18 15:02:24.521(Timestamp), 10(Long)
        // 执行正常的分页查询
        IPage<Employee> page = this.employeeRepository.selectPage(employeeQuery);
        // TODO 注意: 该测试用例是基于 H2 内存数据库 - 执行前没有数据 - 所以插入了一条数据
        //  然后 assert 才会大胆的 写: expected == 1
        Assertions.assertEquals(1, page.getTotal());
    }

    private List<Long> populateIds() {
        return Stream.of("1,2,3".split(",")).map(Long::parseLong).collect(Collectors.toList());
    }

    private void doAssert(Employee employeeModel) {
        Assertions.assertNotNull(employeeModel);
        Assertions.assertEquals("2021109527", employeeModel.getEmployeeNo());
        Assertions.assertEquals(2021109527L, employeeModel.getOrgId());
        Assertions.assertEquals("宇宙漫游者", employeeModel.getOrgName());
        Assertions.assertEquals(1024, employeeModel.getOrderNo());
        Assertions.assertEquals(1, employeeModel.getStatus());
        Assertions.assertEquals("我是备注", employeeModel.getRemark());
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
}