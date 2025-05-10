package com.zc.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * 用于读取 meta.json 文件
 * 有点类似于业务层
 *
 *
 * 单例模式的 MetaManager 类，用于管理全局唯一的 Meta 配置对象。
 */
public class MetaManager {

    private static volatile Meta meta;


    /* 阻止外部通过 new MetaManager() 实例化对象，强制通过静态方法 getMeta() 获取实例，确保单例的唯一性。 */
    public MetaManager() {
    }

    /* 双检锁单例设计模式 */

    /* 多个线程同时进入时因为有锁(synchronized)只会放行一个，第二个if判断可以保证当已经加载一次后其余的线程进来是不会再次加载 */
    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                /* 进入同步块后再次检查 meta 是否为空，防止多个线程通过第一次检查后重复初始化。 */
                if (meta == null) {
                    meta = iniMeta();/* 初始化 */
                }
            }
        }
        return meta;
    }

    private static Meta iniMeta() {

        /* 使用Hutool工具类(ResourceUtil)来读取 src/main/resources这个路径下的  .json文件       (readUtf8Str)指定格式 */
        String metaJson = ResourceUtil.readUtf8Str("meta.json");// 读取文件内容

        /* 使用工具类 JSONUtil 将 JSON 字符串 反序列化为 Meta 类的实例。 */
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        Meta.FileConfig fileConfig = newMeta.getFileConfig();

        return newMeta;
    }

}
