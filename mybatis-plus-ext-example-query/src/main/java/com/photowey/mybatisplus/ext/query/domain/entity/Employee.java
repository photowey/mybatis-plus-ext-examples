/*
 * Copyright © 2021 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photowey.mybatisplus.ext.query.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.photowey.mybatisplus.ext.core.domain.entity.BaseEntity;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * {@code Employee}
 *
 * @author weichangjun
 * @date 2022/02/18
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("Employee")
@TableName(value = "sys_employee")
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity<Employee> {

    /**
     * 主键标识
     */
    private Long id;
    /**
     * 工号
     */
    private String employeeNo;
    /**
     * 隶属机构标识
     */
    private Long orgId;
    /**
     * 隶属机构名称
     */
    private String orgName;
    /**
     * 序号
     */
    private Integer orderNo;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
