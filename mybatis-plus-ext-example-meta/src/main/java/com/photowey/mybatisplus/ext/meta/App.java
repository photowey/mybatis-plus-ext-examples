package com.photowey.mybatisplus.ext.meta;

import com.photowey.mybatisplus.ext.meta.annotation.EnableMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code App}
 *
 * @author weichangjun
 * @date 2022/02/18
 * @since 1.0.0
 */
@SpringBootApplication
@EnableMetaObjectHandler
@MapperScan("com.photowey.mybatisplus.ext.meta.repository")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
