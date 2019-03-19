<#import "layout.ftl" as defaultLayout>
<#if (model.resDataPack)??>
    <#assign resDataPack = model.resDataPack />
</#if>

<@defaultLayout.layout title="首页">
    当前时间：${resDataPack.currentSysDate!""}
</@defaultLayout.layout>
<script>
</script>