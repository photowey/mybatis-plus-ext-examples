package com.photowey.mybatisplus.ext.condition.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.component.FieldHandler;
import com.photowey.mybatisplus.ext.processor.field.FieldContext;
import com.photowey.mybatisplus.ext.processor.util.CriteriaUtils;

/**
 * {@code StatusEmployeeFieldHandler}
 * 处理: {@code status} 字段
 *
 * @author weichangjun
 * @date 2022/02/19
 * @since 1.0.0
 */
@FieldHandler
public class StatusEmployeeFieldHandler implements EmployeeFieldHandler {

    @Override
    public boolean supports(FieldContext context) {
        String columnName = CriteriaUtils.columnName(context.getField(), context.getNaming());
        return "status".equals(columnName);
    }

    @Override
    public boolean handleField(FieldContext context) {
        QueryWrapper queryWrapper = context.getQueryWrapper();
        queryWrapper.or((innerWrapper) -> {
            QueryWrapper wrapper = (QueryWrapper) innerWrapper;
            wrapper.eq("status", 1);
            wrapper.or();
            wrapper.eq("remark", "walk");
        });

        // queryWrapper.nested((wrapper) -> {});

        return true;
    }
}
