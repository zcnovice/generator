package com.zc.maker.generator.Main;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.zc.maker.generator.file.DynamicFileGenerator;
import com.zc.maker.meta.Meta;
import com.zc.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        //测试是否读取到配置文件
        Meta meta = MetaManager.getMetaObject();
        //System.out.println(meta);


        //获取文件输入与输出路径还有模型，通过调用方法对文件进行生成
        /* 输出文件的根路径 */
        //获取当前项目路径
        String projectPath = System.getProperty("user.dir");
        //输出文件的根路径(后面还有路径[是生成的文件的项目路径])
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        //D:\Java113\ProjectTotal\generator\generator-maker       \generated\acm-template-pro-generator
        //System.out.println("outputPath = " + outputPath);

        //判断输出路径是否存在，不存在则创建
        File file = new File(outputPath);
        if(!file.exists()){
            file.mkdirs();
        }

        /* 读取输入路径(先从读取到resources开始) */
/*
        ClassPathResource 是 Spring 框架提供的工具类，用于表示 类路径下的资源.
        空字符串 "" 表示尝试定位类路径的根目录（如 src/main/resources/）.
        然后获取绝对路径
*/
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();



        /* 获取要生成java文件的基础路径 */
        //输出路径的根路径
        String outputBasePackage = meta.getBasePackage();
        //把文件包名修改一下(com.zc)->(co/zc)
        /* join()本身是拼接字符串
        *  split()本身是分割字符串 */
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));

        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;
        System.out.println("outputBaseJavaPackagePath = " + outputBaseJavaPackagePath);


        String inputFilePath;
        String outputFilePath;

        /* 文件路径已经正确，开始生成文件 */
        //拼接一下输入路径
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        //拼接一下输出路径
        outputFilePath = outputBaseJavaPackagePath  + "/model/DataModel.java";
        /* 生成文件 (DataModel.java文件)*/
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);


        /*  cli.command.ConfigCommand  生成(ConfigCommand.java文件) */
        //输入路径
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        //输出路径
        outputFilePath = outputBaseJavaPackagePath  + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

//-----------------------------下面都是一样的--------------------------


        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);


//-------------------------------动静态文件生成--------------------------


        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);


        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);


        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);


    }
}
