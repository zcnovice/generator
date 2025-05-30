package com.zc.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        //获取当前项目的根路径
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        //获取当前项目根路径下要拷贝的文件夹(待拷贝文件路径)
        /*  File.separator 是 Java 中的一个静态字符串常量，用于表示文件路径分隔符。它的值取决于操作系统：
            在 Windows 系统中，File.separator 的值是 \。
            在 Unix/Linux 或 macOS 系统中，File.separator 的值是 /。
            使用 File.separator 可以确保代码在不同操作系统上都能正确解析文件路径，避免硬编码路径分隔符带来的兼容性问题。*/
        String inputPath = projectPath+File.separator +"zcnovice-generator-dome-projects"+ File.separator+"acm-template";
        //输出路径(当前项目根目录)
        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);
    }

    /**
     *  通过hutool复制文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }





//--------------------------------递归实现拷贝文件-------------------------------------------------




    /**
     * 递归拷贝文件（递归实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 文件 A => 目录 B，则文件 A 放在目录 B 下
     * 文件 A => 文件 B，则文件 A 覆盖文件 B
     * 目录 A => 目录 B，则目录 A 放在目录 B 下
     *
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            // 是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
