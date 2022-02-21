package com.photowey.mybatisplus.ext.mapper.repository;

import com.photowey.mybatisplus.ext.mapper.domain.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code BatchEmployeeRepositoryTest}
 *
 * @author weichangjun
 * @date 2022/02/18
 * @since 1.0.0
 */
@SpringBootTest
class BatchEmployeeRepositoryTest {

    @Autowired
    private BatchEmployeeRepository batchEmployeeRepository;

    @Test
    void testHello() {
        Employee employee = this.batchEmployeeRepository.selectById(0L);
        Assertions.assertNull(employee);
    }

    @Test
    void testSaveBatch() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= 1024; i++) {
            Employee target = this.populateEmployee(i);
            employees.add(target);
        }

        this.batchEmployeeRepository.deleteAll();
        boolean batchInserts = this.batchEmployeeRepository.batchInserts(employees, Employee.class);
        Assertions.assertTrue(batchInserts);

        List<Employee> employeesAll = this.batchEmployeeRepository.selectList();
        Assertions.assertEquals(1024, employeesAll.size());
    }

    private Employee populateEmployee(int index) {
        Employee employee = new Employee();
        employee.setEmployeeNo("2021109527" + index);
        employee.setOrgId(2021109527L);
        employee.setOrgName("宇宙漫游者" + index);
        employee.setOrderNo(1024);
        employee.setStatus(1);
        employee.setRemark("我是备注" + index);
        employee.setCreateTime(LocalDateTime.now());
        employee.setCreateBy("20211095278848");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateBy("20211095278848");
        employee.setDeleted(0);

        return employee;
    }

}