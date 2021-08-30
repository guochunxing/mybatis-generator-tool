package com.workabee.mybatis.tool.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.TableConfiguration;

import java.util.List;

/**
 * Author chunxing
 * Date 2018-11-29 17:39
 * Description:
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        TableConfiguration configuration = introspectedTable.getTableConfiguration();

        //添加domain的import
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.IdType");
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.TableField");
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.TableId");
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.TableName");

        //添加domain的注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@TableName(\"" + configuration.getTableName() + "\")");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");

        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }
}
