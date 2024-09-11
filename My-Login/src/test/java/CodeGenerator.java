import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
 
import java.util.Collections;
 
public class CodeGenerator {
    public static void main(String[] args) {
        //使用时修改这个模块名就行了
        String moduleName = "sys";
        String mapperLocation = "C:\\Users\\86158\\IdeaProjects\\My-Login\\src\\main\\resources\\mapper\\" + moduleName;
 
//        设置你需要的表对应的实体类名
        String tables = "x_user,x_role,x_menu,x_user_role,x_role_menu";
 
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mylogin",
                        "root",
                        "200306090016wow")
                .globalConfig(builder -> {
                    builder.author("wow") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\86158\\IdeaProjects\\My-Login\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.mylogin") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                    .addTablePrefix("x_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}