package com.xiesn.thymeleaf.project.system.dict.service;


/**
 * 字典 业务层
 *
 * @author ruoyi
 */
public interface IDictDataService {


    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String selectDictLabel(String dictType, String dictValue);

}
