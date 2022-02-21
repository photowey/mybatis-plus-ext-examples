package com.photowey.mybatisplus.ext.meta.repository;

import com.photowey.mybatisplus.ext.repository.RepositoryExt;
import com.photowey.mybatisplus.ext.meta.domain.entity.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * {@code EmployeeRepository}
 *
 * @author weichangjun
 * @date 2022/02/18
 * @since 1.0.0
 */
public interface EmployeeRepository extends RepositoryExt<Employee> {

    Employee selectDeletedOne(@Param("id") Long id);
}
