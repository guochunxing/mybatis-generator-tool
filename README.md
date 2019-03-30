# mybatis-generator-tool

## 功能点：

1. 重新生成文件，不在追加，改为覆盖
2. db 字段描述直接转化为`javaBean`注释
3. LombokPlugin 自动添加注解 `@Data`、`@Builder`、`@NoArgsConstructor`、`@AllArgsConstructor`
4. `example` 文件指定存放位置,之后准备加上更名

## 使用小技巧：
   可生成基础的`BaseMapper`接口、xml，然后自己的 `Mapper` 继承 `BaseMapper`。在 业务层 注入`Mapper`即可拥有单表的基本操作。 `BaseMapper` 的 `xml`文件的`namespce` 要改成 `Mapper` 对应的 `namespce`

## 以上插件代码，有一部分是我在网上整理过来，感谢他们。

有些人认为自动生成的代码 `sql` 不如自己写优化的好，观点没错。但是认为使用自动生成代码都是垃圾的就不敢恭维了。自动也可以生成sql，只生成 javaBean、空的 xml 文件。

## 使用姿势
 `pom.xml`
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.7</version>
            <configuration>
                <verbose>false</verbose>
                <overwrite>true</overwrite>
            </configuration>
            <dependencies>
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>5.1.46</version>
                </dependency>
                <dependency>
                    <groupId>com.workabee</groupId>
                    <artifactId>mybatis-tool</artifactId>
                    <version>1.0.0</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>

```

``generatorConfig.xml``

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>
    <context id="context" targetRuntime="MyBatis3">
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"/>
        <!--文件覆盖设置-->
        <plugin type="com.workabee.mybatis.tool.plugin.OverIsMergeAblePlugin"/>
        <!--LombokPlugin-->
        <plugin type="com.workabee.mybatis.tool.plugin.LombokPlugin"/>
        <!--example 文件指定存放位置-->
        <plugin type="com.workabee.mybatis.tool.plugin.ExamplePackageClassPlugin">
            <property name="targetPackage" value="com.workabee.bifrost.api.admin.dal.example"/>
            <property name="isProject" value="true"/>
        </plugin>
        <!--db 字段描述 -->
        <commentGenerator type="com.workabee.mybatis.tool.CommentGenerator">
            <property name="suppressAllComments" value="true"/>
            <property name="suppressDate" value="true"/>
        </commentGenerator>
         <!--db 连接 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:8888/test"
                        userId="xxxx" password="yyyy"/>
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <javaModelGenerator targetPackage="com.workabee.dal.model" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="mybatis.base" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </sqlMapGenerator>

        <javaClientGenerator targetPackage="com.workabee.dal.dao.base" type="XMLMAPPER"
                             targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        
        <table tableName="table" mapperName="BaseTableMapper">
             <generatedKey column="id" sqlStatement="Mysql" identity="true"/>
         </table>
    </context>
</generatorConfiguration>
```