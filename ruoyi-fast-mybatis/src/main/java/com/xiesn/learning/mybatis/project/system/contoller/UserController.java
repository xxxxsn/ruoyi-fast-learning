package com.xiesn.learning.mybatis.project.system.contoller;

import com.xiesn.learning.mybatis.framework.web.controller.BaseController;
import com.xiesn.learning.mybatis.framework.web.page.TableDataInfo;
import com.xiesn.learning.mybatis.project.system.domain.User;
import com.xiesn.learning.mybatis.project.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/system/user")
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user) {
        startPage();
        List<User> list = userService.listByAll(user);
        return getDataTable(list);
    }

}