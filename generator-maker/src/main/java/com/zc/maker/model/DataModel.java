package com.zc.maker.model;

import lombok.Data;

/**
 * 动态模版配置
 */
@Data
public class DataModel {

    /**
     * 是否生成循环  (开关)
     */
    public boolean loop;

    /**
     * 作者注释  (填充值)
     */
    private String author;

    /**
     * 输出信息
     */
    private String outputText;
}

