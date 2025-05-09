package com.zc.maker.generator.file;

import com.zc.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class FileGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate1(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath = " + projectPath);
        // 整个项目的根路径(.getParentFile()      获取当前文件的父路径)
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile, "/zcnovice-generator-dome-projects/acm-template").getAbsolutePath();
        System.out.println("inputPath = " + inputPath);
        String outputPath = projectPath;
        //System.out.println("outputPath = " + outputPath);
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);

        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src\\main\\resources\\templates\\MainTemplate.java.ftl";
        System.out.println(inputDynamicFilePath);
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        System.out.println(outputDynamicFilePath);
        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }


    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("zcnovice");
        dataModel.setLoop(true);
        dataModel.setOutputText("求和结果：");
        doGenerate1(dataModel);
    }
}


