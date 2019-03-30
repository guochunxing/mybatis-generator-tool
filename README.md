# mybatis-generator-tool

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