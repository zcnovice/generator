package com.zc.cli.example;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/*  必须要加的注解                                          设置为true表示会提供帮助手册   就是命令-help  */
@Command(name = "ASCIIArt", version = "ASCIIArt 1.0", mixinStandardHelpOptions = true)
public class ASCIIArt implements Runnable {/* 类要实现Runnable中的run方法  */


    /*   这个注解是(通过命令"-s", "--font-size"来给变量进行赋值)   */
    @Option(names = { "-s", "--font-size" }, description = "Font size" ,required = true)
    int fontSize = 19;//给变量的默认值

    /* 可以接受多个数（接收数组）*/
    @Option(names = "-option",arity = "0..5")
    int[] values;


    /* 相当与接收后面所有的单词 */
    @Parameters(paramLabel = "<word>", defaultValue = "Hello, picocli",
            description = "Words to be translated into ASCII art.")
    private String[] words = { "Hello,", "picocli" };//给变量的默认值

    /* 用户确认后执行的方法(如在命令行中敲回车) */
    @Override
    public void run() {
        // 自己实现业务逻辑
        System.out.println("fontSize = " + fontSize);
        System.out.println("words = " + String.join(",", words));
        //增强for循环遍历数组
        for (int i : values) {
            System.out.println("values = " + i);
        }
    }

    public static void main(String[] args) {

        /*创建命令行工具                    要绑定的命令       把用户输入的值传递给变量*/
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }
}

