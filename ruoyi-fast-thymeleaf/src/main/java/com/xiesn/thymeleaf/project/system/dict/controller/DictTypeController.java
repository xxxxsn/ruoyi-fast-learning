package com.xiesn.thymeleaf.project.system.dict.controller;

import com.xiesn.thymeleaf.framework.web.controller.BaseController;
import com.xiesn.thymeleaf.framework.web.page.TableDataInfo;
import com.xiesn.thymeleaf.project.system.dict.domain.DictType;
import com.xiesn.thymeleaf.project.system.dict.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict")
public class DictTypeController extends BaseController {
    private String prefix = "system/dict/type";

    @Autowired
    private IDictTypeService dictTypeService;

    @GetMapping()
    public String dictType() {
        return prefix + "/type";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DictType dictType) {
        startPage();
        List<DictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }


}
