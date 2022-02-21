package com.photowey.mybatisplus.ext.meta.handler;

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
    public String loadOperator() {
        return "UserInfo" + "Meta";
    }
}
