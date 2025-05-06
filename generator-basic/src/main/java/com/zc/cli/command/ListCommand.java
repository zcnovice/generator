package com.zc.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 遍历所有要生成的文件列表
 */
@CommandLine.Command(name = "list", description = "列出所有要生成的文件列表",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    @Override
    public  void run() {
        //获取文件根路径
        String projectPath = System.getProperty("user.dir");
        /* 转换成文件对象      并且获取父级目录 */
        File file = new File(projectPath).getParentFile();
        //输入路径
        /* getAbsolutePath() 是 Java 中 File 类的一个方法，它的作用是返回文件或目录的绝对路径。*/
        String inputPath = new File(file, "/zcnovice-generator-dome-projects/acm-template").getAbsolutePath();

        /* List<File> files = FileUtil.loopFiles(inputPath); 这行代码的作用是递归遍历指定目录下的所有文件，并将结果以 List<File> 的形式返回。 */
        List<File> files = FileUtil.loopFiles(inputPath);
        for(File file1:files){
            System.out.println(file1);
        }
    }

}
