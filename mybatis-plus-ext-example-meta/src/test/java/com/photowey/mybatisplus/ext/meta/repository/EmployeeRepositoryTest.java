package com.photowey.mybatisplus.ext.meta.repository;

import com.photowey.mybatisplus.ext.core.domain.operator.Operator;
import com.photowey.mybatisplus.ext.meta.domain.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void testInsertFill() {
        Employee employee = this.populateEmployee();
        this.employeeRepository.insert(employee);

        Employee selectOne = this.employeeRepository.selectOne(Employee::getEmployeeNo, "2021109527" + "Meta");
        Assertions.assertNotNull(selectOne);

        this.doAssert(selectOne);
    }

    @Test
    void testLogicDelete() {
        Employee employee = this.populateEmployee();
        this.employeeRepository.insert(employee);

        this.employeeRepository.deleteById(employee.getId());
        // 不能执行 selectById() - 因为 逻辑删除-会改写语句: DELETED=0 - 导致查询不到数据
        Employee deleted = this.employeeRepository.selectDeletedOne(employee.getId());
        Assertions.assertNotNull(deleted);
        Assertions.assertEquals(1, deleted.getDeleted());
        doAssert(deleted);
    }

    private void doAssert(Employee employeeModel) {
        Assertions.assertNotNull(employeeModel);
        Assertions.assertEquals("2021109527" + "Meta", employeeModel.getEmployeeNo());
        Assertions.assertEquals(2021109527L, employeeModel.getOrgId());
        Assertions.assertEquals("宇宙漫游者" + "Meta", employeeModel.getOrgName());
        Assertions.assertEquals(1024, employeeModel.getOrderNo());
        Assertions.assertEquals(1, employeeModel.getStatus());
        Assertions.assertEquals("我是备注" + "Meta", employeeModel.getRemark());

        // @see com.photowey.mybatisplus.ext.meta.handler.OperatorHandlerImpl.tryAcquireOperator
        Operator operator = Operator.builder()
                .operatorId(20211095278848L)
                .build();

        Assertions.assertEquals(operator.getOperatorId(), employeeModel.getCreateBy());
        Assertions.assertEquals(operator.getOperatorId(), employeeModel.getUpdateBy());
    }

    private Employee populateEmployee() {
        Employee employee = new Employee();
        employee.setId(1494377100172378113L);
        employee.setEmployeeNo("2021109527" + "Meta");
        employee.setOrgId(2021109527L);
        employee.setOrgName("宇宙漫游者" + "Meta");
        employee.setOrderNo(1024);
        employee.setStatus(1);
        employee.setRemark("我是备注" + "Meta");

        return employee;
    }

}