package com.zc.maker.generator.Main;


import freemarker.template.TemplateException;

import java.io.IOException;

public class MainGenerator extends GenerateTemplate{

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator  mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }

    //比如我不想生成dist方法
    /* 重写buildDist方法 */
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("dist方法不执行");
    }
}
