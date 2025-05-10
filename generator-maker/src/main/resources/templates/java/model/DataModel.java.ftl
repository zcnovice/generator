package ${basePackage}.model;

import lombok.Data;

/**
* 数据模型
*/
@Data
public class DataModel {
<#--
#相当于  foreach，进行循环遍历

然后把modelConfig.models换了一个名字   modelInfo
-->
<#list modelConfig.models as modelInfo>
    <#--(??)相当于判断是否为空   为空就不执行里面的语句-->
    <#if modelInfo.description??>
        /**
        * ${modelInfo.description}
        */
    </#if>
    private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#list>
}
