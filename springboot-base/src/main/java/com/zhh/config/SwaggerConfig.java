package com.zhh.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 添加Swagger2支持
     * @param apiInfo Swagger2的文档信息;会自动使用bean注入到参数
     */
    @Bean(name = "apiDocket")
    public Docket apiDocket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)/*使用Swagger2构建文档*/
                .apiInfo(apiInfo)/*设置文档的API信息*/
                .directModelSubstitute(LocalDate.class, String.class)
                .useDefaultResponseMessages(false)//禁用默认的响应
                .globalResponseMessage(RequestMethod.POST,          //根据自己的需要设计响应
                        Arrays.asList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 message")
                                        .responseModel(new ModelRef("Error"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbidden!")
                                        .build()))
                .select()/*构建一个Swagger的选择器，用于指定对哪些代码需要扫描生成API文档*/
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))/*仅扫描使用了 @Api 注解的类*/
                .paths(PathSelectors.any())/*任意路径*/
                .build();
    }

    /**
     * Swagger2的文档信息 <br/>
     * 从属性文件注入
     * @param title 文档标题；<br/>
     * 		     从disconf的属性文件中使用SpEL表达式的注解  @Value 获取值，未获取到采用默认值
     * @param description 文档描述
     * @param version 文档版本
     * @param license 项目license版权许可组织
     * @param licenseUrl 项目license版权许可组织地址
     * @param termsOfServiceUrl 团队主页地址
     * @param contactName 联系人姓名
     * @param contactUrl 联系人主页地址
     * @param contactEmail 联系人邮箱
     */
    @Bean(name = "apiInfo")
    public ApiInfo apiInfo(@Value("${swagger.apiinfo.title:后台服务接口文档}") String title,
                           @Value("${swagger.apiinfo.description:提供数据的相关接口}") String description,
                           @Value("${swagger.apiinfo.version:V1.0}") String version,
                           @Value("${swagger.apiinfo.license:Apache 2.0}") String license,
                           @Value("${swagger.apiinfo.licenseUrl:http://www.apache.org/licenses/LICENSE-2.0}") String licenseUrl,
                           @Value("${swagger.apiinfo.termsofserviceurl:http://www.apache.org}") String termsOfServiceUrl,
                           @Value("${swagger.apiinfo.contact.name:程序猿GG}") String contactName,
                           @Value("${swagger.apiinfo.contact.url:http://www.apache.org}") String contactUrl,
                           @Value("${swagger.apiinfo.contact.email:zhouhui5116@163.com}") String contactEmail) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .license(license)
                .licenseUrl(licenseUrl)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .build();
    }

}
