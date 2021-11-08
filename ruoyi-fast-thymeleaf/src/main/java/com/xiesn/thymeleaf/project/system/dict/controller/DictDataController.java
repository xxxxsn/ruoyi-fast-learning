package com.xiesn.thymeleaf.project.system.dict.controller;

import com.xiesn.thymeleaf.framework.web.controller.BaseController;
import com.xiesn.thymeleaf.project.system.dict.service.IDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict/data")
public class DictDataController extends BaseController {
    private String prefix = "system/dict/data";

    @Autowired
    private IDictDataService dictDataService;

    @GetMapping()
    public String dictData() {
        return prefix + "/data";
    }


}
