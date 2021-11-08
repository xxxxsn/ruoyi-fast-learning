package com.xiesn.thymeleaf.project.system.dict.service;

import com.xiesn.thymeleaf.common.utils.StringUtils;
import com.xiesn.thymeleaf.project.system.dict.domain.DictData;
import com.xiesn.thymeleaf.project.system.dict.domain.DictType;
import com.xiesn.thymeleaf.project.system.dict.mapper.DictDataMapper;
import com.xiesn.thymeleaf.project.system.dict.mapper.DictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class DictTypeServiceImpl implements IDictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<DictType> selectDictTypeList(DictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<DictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<DictData> selectDictDataByType(String dictType) {
        List<DictData> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            // DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }


    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {
        List<DictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (DictType dict : dictTypeList) {
            List<DictData> dictDatas = dictDataMapper.selectDictDataByType(dict.getDictType());
        }
    }



}
