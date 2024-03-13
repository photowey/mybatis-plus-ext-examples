package com.photowey.mybatisplus.ext.meta.handler;

import com.photowey.mybatisplus.ext.core.domain.operator.Operator;
import com.photowey.mybatisplus.ext.meta.operator.OperatorHandler;
import org.springframework.stereotype.Component;

/**
 * {@code OperatorHandlerImpl}
 *
 * @author weichangjun
 * @date 2022/02/18
 * @since 1.0.0
 */
@Component
public class OperatorHandlerImpl implements OperatorHandler {

    @Override
    public Operator tryAcquireOperator() {
        // 加载当前操作人
        return Operator.builder()
                .operatorId(20211095278848L)
                .build();
    }
}
