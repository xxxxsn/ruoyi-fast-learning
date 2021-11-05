package com.xiesn.learning.mybatis;

import com.xiesn.learning.mybatis.project.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RuoyiFastMybatisApplicationTests {

    @Resource
    UserService UserService;

    @Test
    public void test() {
    }

}
