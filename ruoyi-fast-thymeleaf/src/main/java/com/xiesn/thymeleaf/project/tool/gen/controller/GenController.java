package com.xiesn.thymeleaf.project.tool.gen.controller;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.fastjson.JSON;
import com.xiesn.thymeleaf.common.utils.StringUtils;
import com.xiesn.thymeleaf.common.utils.text.Convert;
import com.xiesn.thymeleaf.framework.web.controller.BaseController;
import com.xiesn.thymeleaf.framework.web.domain.AjaxResult;
import com.xiesn.thymeleaf.framework.web.domain.CxSelect;
import com.xiesn.thymeleaf.framework.web.page.TableDataInfo;
import com.xiesn.thymeleaf.project.tool.gen.domain.GenTable;
import com.xiesn.thymeleaf.project.tool.gen.domain.GenTableColumn;
import com.xiesn.thymeleaf.project.tool.gen.service.IGenTableColumnService;
import com.xiesn.thymeleaf.project.tool.gen.service.IGenTableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/gen")
public class GenController extends BaseController {
    private String prefix = "tool/gen";

    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    @GetMapping()
    public String gen() {
        return prefix + "/gen";
    }

    /**
     * 查询代码生成列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo genList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据库列表
     */
    @PostMapping("/db/list")
    @ResponseBody
    public TableDataInfo dataList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @PostMapping("/column/list")
    @ResponseBody
    public TableDataInfo columnList(GenTableColumn genTableColumn) {
        TableDataInfo dataInfo = new TableDataInfo();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(genTableColumn);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构
     */
    @GetMapping("/importTable")
    public String importTable() {
        return prefix + "/importTable";
    }

    /**
     * 创建表结构
     */
    @GetMapping("/createTable")
    public String createTable() {
        return prefix + "/createTable";
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    @ResponseBody
    public AjaxResult importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return AjaxResult.success();
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping("/edit/{tableId}")
    public String edit(@PathVariable("tableId") Long tableId, ModelMap mmap) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> genTables = genTableService.selectGenTableAll();
        List<CxSelect> cxSelect = new ArrayList<CxSelect>();
        for (GenTable genTable : genTables) {
            if (!StringUtils.equals(table.getTableName(), genTable.getTableName())) {
                CxSelect cxTable = new CxSelect(genTable.getTableName(), genTable.getTableName() + '：' + genTable.getTableComment());
                List<CxSelect> cxColumns = new ArrayList<CxSelect>();
                for (GenTableColumn tableColumn : genTable.getColumns()) {
                    cxColumns.add(new CxSelect(tableColumn.getColumnName(), tableColumn.getColumnName() + '：' + tableColumn.getColumnComment()));
                }
                cxTable.setS(cxColumns);
                cxSelect.add(cxTable);
            }
        }
        mmap.put("table", table);
        mmap.put("data", JSON.toJSON(cxSelect));
        return prefix + "/edit";
    }

    /**
     * 修改保存代码生成业务
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return AjaxResult.success();
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        genTableService.deleteGenTableByIds(ids);
        return AjaxResult.success();
    }

    @PostMapping("/createTable")
    @ResponseBody
    public AjaxResult create(String sql) {
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
        List<String> tableNames = new ArrayList<>();
        for (SQLStatement sqlStatement : sqlStatements) {
            if (sqlStatement instanceof MySqlCreateTableStatement) {
                MySqlCreateTableStatement createTableStatement = (MySqlCreateTableStatement) sqlStatement;
                String tableName = createTableStatement.getTableName();
                tableName = tableName.replaceAll("`", "");

                int msg = genTableService.createTable(createTableStatement.toString());
                if (msg == 0) {
                    tableNames.add(tableName);
                }
            } else {
                return AjaxResult.error("请输入建表语句");
            }
        }
        List<GenTable> tableList = genTableService.selectDbTableListByNames((tableNames.toArray(new String[tableNames.size()])));
        genTableService.importGenTable(tableList);
        return AjaxResult.success();
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    @ResponseBody
    public AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return AjaxResult.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @GetMapping("/genCode/{tableName}")
    @ResponseBody
    public AjaxResult genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return AjaxResult.success();
    }

    /**
     * 同步数据库
     */
    @GetMapping("/synchDb/{tableName}")
    @ResponseBody
    public AjaxResult synchDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return AjaxResult.success();
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}