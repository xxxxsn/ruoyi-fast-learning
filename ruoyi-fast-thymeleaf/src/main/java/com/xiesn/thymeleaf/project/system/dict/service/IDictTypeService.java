package com.xiesn.thymeleaf.project.system.dict.service;


import com.xiesn.thymeleaf.project.system.dict.domain.DictData;
import com.xiesn.thymeleaf.project.system.dict.domain.DictType;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author ruoyi
 */
public interface IDictTypeService
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<DictType> selectDictTypeList(DictType dictType);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<DictType> selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<DictData> selectDictDataByType(String dictType);

    /**
     * 加载字典缓存数据
     */
    public void loadingDictCache();

}
