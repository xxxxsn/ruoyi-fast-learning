package com.xiesn.generator;

import com.alibaba.fastjson.JSON;
import com.xiesn.generator.project.system.domain.User;
import com.xiesn.generator.project.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RuoyiFastGeneratorApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public  void contextLoads() {

        final List<User> users = userService.listByAll(null);
         System.err.println(JSON.toJSONString(users));
    }

}
