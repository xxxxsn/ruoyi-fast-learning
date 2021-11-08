package com.xiesn.mybatisplus.project.system.contoller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiesn.mybatisplus.framework.web.controller.BaseController;
import com.xiesn.mybatisplus.framework.web.page.TableDataInfo;
import com.xiesn.mybatisplus.project.system.domain.SysUser;
import com.xiesn.mybatisplus.project.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class SysUserController extends BaseController {

    private final SysUserService userService;

    /**
     * pagehelper 分页
     * @param user
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.list(null);
        return getDataTable(list);
    }

    /**
     * mybatisplus 自带分页
     * @param page
     * @return
     */
    @GetMapping("/page")
    @ResponseBody
    public TableDataInfo page(Page<SysUser> page) {
        final Page<SysUser> userPage = userService.page(page);
        return getDataTable(userPage.getRecords());
    }

}