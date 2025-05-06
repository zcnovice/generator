package com.zc.cli;

import com.zc.cli.command.ConfigCommand;
import com.zc.cli.command.GenerateCommand;
import com.zc.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

//命令执行器，负责绑定所有子命令
@Command(name = "command", description = "命令执行器",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{

/*
    初始化块：在类实例化时自动执行，用于配置 commandLine 对象。
*/
    private final CommandLine commandLine;


    //子命令绑定
    {
        commandLine = new CommandLine(this)
                .addSubcommand(new ListCommand())
                .addSubcommand( new GenerateCommand())
                .addSubcommand( new ConfigCommand());
    }



    @Override
    public void run() {
        //不输入子命令时给出的提示
        System.out.println("请输入具体子命令，或者输入 --help 查看命令提示");
    }


    /*外部调用此方法以执行命令行操作。*/
    public Integer doExecute(String[] args){
        return commandLine.execute(args);
    }
}
