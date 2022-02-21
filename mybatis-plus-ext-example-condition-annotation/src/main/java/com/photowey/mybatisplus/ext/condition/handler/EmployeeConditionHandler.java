package com.photowey.mybatisplus.ext.condition.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.And;
import com.photowey.mybatisplus.ext.annotation.Or;
import com.photowey.mybatisplus.ext.annotation.component.ConditionHandler;
import com.photowey.mybatisplus.ext.processor.condition.AbstractConditionHandlerAdaptor;
import com.photowey.mybatisplus.ext.processor.field.FieldContext;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;

/**
 * {@code EmployeeConditionHandler}
 *
 * @author weichangjun
 * @date 2022/02/19
 * @since 1.0.0
 */
@ConditionHandler
public class EmployeeConditionHandler extends AbstractConditionHandlerAdaptor {

    @Autowired
    private List<EmployeeFieldHandler> employeeFieldHandlers;

    @Override
    public void handleAnd(QueryWrapper queryWrapper, AbstractQuery query, Field field) {
        And annotation = field.getAnnotation(And.class);
        Object columnValue = this.columnValue(field, query);
        if (this.isNullOrEmpty(annotation) || this.isNullOrEmpty(columnValue)) {
            return;
        }
        //
        // queryWrapper.and((wrapper)->{});
        // OR
        FieldContext context = new FieldContext(queryWrapper, query, field, annotation.naming());
        // 根据不同的字段来处理
        for (EmployeeFieldHandler employeeFieldHandler : this.employeeFieldHandlers) {
            if (employeeFieldHandler.supports(context)) {
                employeeFieldHandler.handleField(context);
            }
        }
    }

    @Override
    public void handleOr(QueryWrapper queryWrapper, AbstractQuery query, Field field) {
        Or annotation = field.getAnnotation(Or.class);
        Object columnValue = this.columnValue(field, query);
        if (this.isNullOrEmpty(annotation) || this.isNullOrEmpty(columnValue)) {
            return;
        }
        //
        // queryWrapper.or((wrapper)->{});
        // OR
        FieldContext context = new FieldContext(queryWrapper, query, field, annotation.naming());
        // 根据不同的字段来处理
        for (EmployeeFieldHandler employeeFieldHandler : this.employeeFieldHandlers) {
            if (employeeFieldHandler.supports(context)) {
                employeeFieldHandler.handleField(context);
            }
        }
    }
}
