import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器（覆盖模式）
 *
 * @author odboy
 * @version jdk1.8
 * @date 2020/3/13 4:21
 */
@NoArgsConstructor
public final class MySqlGenerator {
    /**
     * 数据库配置
     */
    private String dbIp = "localhost";
    private String dbPort = "3306";
    private String dbName = "peach";
    private String dbParams = "useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private String dbUrl = StrUtil.format("jdbc:mysql://{}:{}/{}?{}", dbIp, dbPort, dbName, dbParams);
    /**
     * 代码输出目录
     */
    private String outputDir;
    /**
     * mapperXml文件路径
     */
    private String xmlMapperPath;
    /**
     * 包结构路径名称
     */
    private String packageRootName;
    /**
     * 待生成表配置
     */
    private String[] tableNames;

    public MySqlGenerator(String outputDir, String packageRootName, String xmlMapperPath, String[] tableNames) {
        this.outputDir = outputDir;
        this.xmlMapperPath = xmlMapperPath;
        this.packageRootName = packageRootName;
        this.tableNames = tableNames;
    }

    public void build() {
        if (this.tableNames == null || this.tableNames.length == 0) {
            throw new RuntimeException("至少得有一张表");
        }
        if (StringUtils.isEmpty(this.outputDir)) {
            throw new RuntimeException("代码输出目录不能为空");
        }
        if (StringUtils.isEmpty(this.packageRootName)) {
            throw new RuntimeException("包结构路径名称不能为空");
        }
        if (StringUtils.isEmpty(this.xmlMapperPath)) {
            throw new RuntimeException("mapperXml文件路径不能为空");
        }
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + this.outputDir);
        // 设置用户名
        gc.setAuthor("odboy");
        gc.setOpen(false);
        // 自定义文件命名，%s 会自动填充表实体属性
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        // service 命名方式
        gc.setServiceName("%sService");
        // service impl 命名方式
        gc.setServiceImplName("%sServiceImpl");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回
                System.out.println("转换类型：" + fieldType);
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.DATE;
                }
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.INTEGER;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        /// pc.setModuleName(scanner("模块名"));
        pc.setParent(this.packageRootName);
        pc.setEntity("model");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("rest");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + MySqlGenerator.this.xmlMapperPath + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 实体是否使用Lombok插件
        strategy.setEntityLombokModel(true);
        // 控制层是否使用Rest风格
        strategy.setRestControllerStyle(true);

        // 自动填充设置
        // 表的自动填充字段
        List<TableFill> tableFillList = new ArrayList<>();
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        tableFillList.add(createTime);
        tableFillList.add(updateTime);
        strategy.setTableFillList(tableFillList);
        /// 指定生成的bean的数据库表名
        strategy.setInclude(this.tableNames);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(false);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static void main(String[] args) {
        String outputDir = "/peach-system/src/main/java";
        String moduleTemplate = "cn.odboy.extra.{}";
        String moduleXmlTemplate = "/peach-system/src/main/resources/mappers/{}";
        new MySqlGenerator(
                outputDir,
                StrUtil.format(moduleTemplate, "cmdb"),
                StrUtil.format(moduleXmlTemplate, "cmdb"),
                new String[]{
                        "tb_cmdb_biz_line"
                }
        ).build();
    }
}
