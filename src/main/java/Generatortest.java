import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by badado on 2019/4/25.
 */
public class Generatortest {
    public static void main(String[] args) {
        //全局配置
        GlobalConfig config = new GlobalConfig();
        //设置是否支持AR模式
        config.setActiveRecord(true)
                //设置生成代码的作者
                .setAuthor("badaoliumangqizhi")
                //设置输出代码的位置E:\IdeaProjects\logistics-csp-admin\src\main\java\com\cps\csp\work
                .setOutputDir("e:/IdeaProjects/logistics-csp-admin/src/main/java/")
                .setSwagger2(true)
                //.setEnableCache(false)// XML 二级缓存
                //.setBaseResultMap(true)// XML ResultMap
                //.setBaseColumnList(true)// XML columList
                //.setKotlin(true) 是否生成 kotlin 代码
                //设置是否覆盖原来的代码
                .setFileOverride(true);


//        url: jdbc:mysql://10.16.74.160:3306/logistics_csp?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&autoReconnect=true&serverTimezone=GMT%2B8
//        username: logistics_dev
//        password: ufCO9gg3j
       //******************************数据源配置***************************************
        //数据库连接url jdbc:mysql://10.16.119.226:3306/logistics_csp
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/logig_csp";
        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //数据库类型 枚举
        dataSourceConfig.setDbType(DbType.MYSQL)
                //设置url
                .setUrl(dbUrl)
                //设置用户名
                .setUsername("XXX")
                //设置密码
                .setPassword("XXX")
                //设置数据库驱动
                .setDriverName("com.mysql.cj.jdbc.Driver")
                // 自定义数据库表字段类型转换【可选】
//                .setTypeConvert(new MySqlTypeConvert() {
//                    @Override
//                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                        System.out.println("转换类型：" + fieldType);
//                        //tinyint转换成Boolean
//                         if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
//                            return DbColumnType.BOOLEAN;
//                         }
//                         //将数据库中datetime转换成date
//                        if ( fieldType.toLowerCase().contains( "datetime" ) ) {
//                            return DbColumnType.DATE;
//                        }
//                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
//                    }
//                })
        ;



        //******************************策略配置******************************************************
        // 自定义需要填充的字段 数据库中的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("gmt_modified", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("modifier_id", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("creator_id", FieldFill.INSERT));
        tableFillList.add(new TableFill("gmt_creat", FieldFill.INSERT));
        tableFillList.add(new TableFill("available_flag", FieldFill.INSERT));
        tableFillList.add(new TableFill("deleted_flag", FieldFill.INSERT));
        tableFillList.add(new TableFill("sync_flag", FieldFill.INSERT));
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                //全局大写命名是否开启
                .setCapitalMode(true)
                //【实体】是否为lombok模型
                .setEntityLombokModel(true)
                //表名生成策略  下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                //自动填充设置
                .setTableFillList(tableFillList)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude("cps_post");
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                map.put("swagger",true);
                this.setMap(map);
            }
        };

          //   自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/vm/mybatisplus/query.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return config.getOutputDir() +"com/cps/csp/work/domain/query/"  + tableInfo.getEntityName() + "Query.java";
            }
        });
        focList.add(new FileOutConfig("/vm/mybatisplus/vo.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return config.getOutputDir() +"com/cps/csp/work/domain/vo/"  + tableInfo.getEntityName() + "Vo.java";
            }
        });
        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

//        InjectionConfig injectionConfig = new InjectionConfig() {
//            @Override
//            public void initMap() {
//
//                /**自定义生成模板参数**/
////                Map<String, Object> paramMap = new HashMap<>();
//
//                /** 自定义 生成类**/
//                Map<String, Object> customFileMap = new HashMap<>();
//                customFileMap.put("query"+File.separator+"%sQuery.java", "vm/mybatisplus/query.java.vm");
//                /**Vo实体**/
////                customFileMap.put("vo"+File.separator+"%sVO.java", "/templates/VO.java.vm");
//                /**DTO实体**/
////                customFileMap.put("dto"+File.separator+"%sDTO.java", "/templates/DTO.java.vm");
//                this.setMap(customFileMap);
//                this.setFileOutConfigList((new FileOutConfig("vm/mybatisplus/query.java.vm")));
//            }
//        };
        //集成注入设置
        //注入全局设置
        new AutoGenerator().setGlobalConfig(config)
                //注入数据源配置
                .setDataSource(dataSourceConfig)
                //注入策略配置
                .setStrategy(strategyConfig)
                //设置包名信息
                .setPackageInfo(
                        new PackageConfig()
                                //提取公共父级包名
                                .setParent("com.cps.csp.work")
                                //设置controller信息
                                .setController("controller")
                                //设置实体类信息
                                .setEntity("domain")
                ).setCfg(cfg)
                //设置自定义模板
                .setTemplate(
                        new TemplateConfig()
                                //.setXml(null)//指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
                                //注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
                                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                                 .setController("vm/mybatisplus/controller.java.vm")
//                                    .setEntity("vm/java/domain.java")
//                                 .setMapper("vm/java/mapper.java")
                                // .setXml("...");
                                 .setService("vm/mybatisplus/service.java")
                                 .setServiceImpl("vm/mybatisplus/serviceImpl.java")
                )
                //开始执行代码生成
                .execute();
    }


}