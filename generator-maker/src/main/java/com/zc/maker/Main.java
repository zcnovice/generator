package com.zc.maker;

import com.zc.maker.cli.CommandExecutor;
import com.zc.maker.generator.Main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}