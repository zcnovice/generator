package com.zc.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.zc.generator.MainGenerator;
import com.zc.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * 核心命令，接收参数并执行命令
 */
@CommandLine.Command(name = "generate", description = "生成代码",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    /**
     * 是否生成循环  (开关)
     */
    @CommandLine.Option(names = {"-l", "--loop"}, description = "是否生成循环",arity = "0..1",interactive = true,echo = true)
    private boolean loop;

    /**
     * 作者注释  (填充值)
     */
    @CommandLine.Option(names = {"-a", "--author"}, description = "作者注释",interactive = true,echo = true,arity = "0..1")
    private String author = "zcnovice";

    /**
     * 输出信息
     */
    @CommandLine.Option(names = {"-o","--outputText"},description = "输出文本",defaultValue = "hello world",interactive = true,echo = true,arity = "0..1")
    private String outputText;


    @Override
    public Integer call() throws Exception {
        //new一个该类对象
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();

        /* 属性拷贝：自动将 this（当前对象）的所有与 mainTemplateConfig 同名且类型兼容 的属性值，复制到 mainTemplateConfig 对象中。 */
        BeanUtil.copyProperties(this,mainTemplateConfig);
        System.out.println("配置信息："+mainTemplateConfig);
        //这个方法是在生成代码（调用doGenerate1来生成）。
        MainGenerator.doGenerate1(mainTemplateConfig);
        return 0;
    }
}
