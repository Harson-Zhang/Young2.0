package cn.zhx2019.young.course.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author： CatalpaFlat
 * @Descrition:
 * @Date: Create in 11:26 2017/11/14
 * @Modified BY：
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan(basePackages ="cn.zhx2019.young.user.controller")
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chen"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SSM-Swagger2 APIs")
                .termsOfServiceUrl("http://blog.csdn.net/dushiwodecuo")
                .contact(new Contact("Young2.0","www.zhx2019.cn","zhxharson@qq.com"))
                .version("1.0.0")
                .build();
    }

}

