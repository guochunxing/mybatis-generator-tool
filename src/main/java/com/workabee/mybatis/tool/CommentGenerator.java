package com.workabee.mybatis.tool;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * Author: chunxing
 * Date: 2018/7/21  下午2:06
 * Description: 获取数据库字段描述，自动添加为注解
 */
public class CommentGenerator extends DefaultCommentGenerator {

    public CommentGenerator() {
        super();
    }


    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        if (!introspectedColumn.getRemarks().trim().equals("")) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
            field.addJavaDocLine(" */");
        }


        String actualColumnName = introspectedColumn.getActualColumnName();
        if (actualColumnName.equals("id")) {
            //主键
            field.addAnnotation("@TableId(value = \"id\", type = IdType.AUTO)");
        } else {
            field.addAnnotation("@TableField( \"" + actualColumnName + "\")");
        }
    }
}
