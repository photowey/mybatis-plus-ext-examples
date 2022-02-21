package com.photowey.mybatisplus.ext.condition.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.component.FieldHandler;
import com.photowey.mybatisplus.ext.processor.field.FieldContext;
import com.photowey.mybatisplus.ext.processor.util.CriteriaUtils;

/**
 * {@code OrgNameEmployeeFieldHandler}
 * 处理 {@code org_name} 字段
 *
 * @author weichangjun
 * @date 2022/02/19
 * @since 1.0.0
 */
@FieldHandler
public class OrgNameEmployeeFieldHandler implements EmployeeFieldHandler {

    @Override
    public boolean supports(FieldContext context) {
        String columnName = CriteriaUtils.columnName(context.getField(), context.getNaming());
        return "org_name".equals(columnName);
    }

    @Override
    public boolean handleField(FieldContext context) {
        QueryWrapper queryWrapper = context.getQueryWrapper();
        Object columnValue = CriteriaUtils.columnValue(context.getField(), context.getQuery());
        queryWrapper.and((innerWrapper) -> {
            QueryWrapper wrapper = (QueryWrapper) innerWrapper;
            wrapper.eq("org_name", columnValue);
            wrapper.or();
            wrapper.likeRight("org_name", columnValue);
        });

        // queryWrapper.nested((wrapper) -> {});

        return true;
    }
}
