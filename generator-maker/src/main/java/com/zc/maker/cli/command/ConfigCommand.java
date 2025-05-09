package com.zc.maker.cli.command;


import com.zc.maker.model.DataModel;
import picocli.CommandLine;
import cn.hutool.core.util.ReflectUtil;
import java.lang.reflect.Field;

/**
 * config 是一个辅助命令，作用是输出允许用户传入的动态参数的信息（也就是本项目 MainTemplateConfig 类的字段信息）。
 */
@CommandLine.Command(name = "config", description = "输出允许用户传入的动态参数的信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("查看参数信息");

        /* 用的Hutool的工具类进行反射 */
        Field[] fields = ReflectUtil.getFields(DataModel.class);

        // 遍历字段，输出字段名、字段类型、字段注释
        for (Field field : fields) {
            System.out.println("字段名：" + field.getName());
            System.out.println("字段类型：" + field.getType());
            //System.out.println("字段注释：" + field.getAnnotation(CommandLine.Option.class).description());
            System.out.println("----");
        }

    }
}
