package com.xiesn.thymeleaf.project.system.dict.service;

import com.xiesn.thymeleaf.project.system.dict.mapper.DictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class DictDataServiceImpl implements IDictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }
}
