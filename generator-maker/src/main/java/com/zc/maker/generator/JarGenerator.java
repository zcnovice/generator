package com.zc.maker.generator;

import java.io.*;

public class JarGenerator {

    /* 给项目打jar包 */
    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        // 清理之前的构建并打包
        // 注意不同操作系统，执行的命令不同
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";//Windows系统
        String otherMavenCommand = "mvn clean package -DskipTests=true";//其他系统
        String mavenCommand = winMavenCommand;


        /* ProcessBuilder 是 Java 中用于创建操作系统进程的类 */
        /* 这里一定要拆分！    通过空格来拆解命令    */
        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));

        /* 指定文件路径(projectDir)就是外层传过来的项目路径 */
        processBuilder.directory(new File(projectDir));

        /* 启动进程 */
        //相当于是打开终端执行命令行命令
        Process process = processBuilder.start();

        // 读取命令的输出
        /* Process 对象代表一个外部进程，通过 getInputStream() 可以拿到该进程的输出流。
        * 这个流是字节流（InputStream），需要转换为字符流才能方便读取文本。  */
        InputStream inputStream = process.getInputStream();
        /* 将字节流转换为字符流，并用缓冲方式高效读取。 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        /* 循环读取输出流的每一行，直到流结束（返回 null） */
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令执行完成
        /* 阻塞当前线程直到进程结束，返回退出码（0 表示成功，非 0 表示失败） */
        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码：" + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("D:\\Java113\\ProjectTotal\\generator\\generator-maker\\generated\\acm-template-pro-generator");
    }
}
