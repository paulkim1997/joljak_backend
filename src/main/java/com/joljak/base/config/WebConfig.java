package com.joljak.base.config;

import com.joljak.auth.JwtInterceptor;
import com.joljak.xlsx.view.ExcelXlsxStreamingView;
import com.joljak.xlsx.view.ExcelXlsxView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // URL과 이미지 Resource 연결
    private final String uploadImagesPath;

    public WebConfig(@Value("${file.upload-dir}") String uploadImagesPath) {
        this.uploadImagesPath = uploadImagesPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        //List<String> imageFolders = Arrays.asList("alloc","etc");
        List<String> imageFolders = Arrays.asList("alloc");

        for (String imageFolder : imageFolders) {
            registry.addResourceHandler("/static/image/" + imageFolder + "/**")
                    .addResourceLocations("file:///" + uploadImagesPath + imageFolder + "/")
                    .setCachePeriod(3600)
                    .resourceChain(true)
                    .addResolver(new PathResourceResolver());
        }
    }


    //아래에 기재한 경로는 접속가능하게 허용
    private static final String[] EXCLUDE_PATHS = {
            "/api/auth/**"
            , "/swagger-ui.html"
            , "/webjars/**"
            , "/swagger-resources/**"
            , "/v2/**"
            , "/swagger/**"
            , "/api/xlsx/**"
            , "/static/image/**"
    };

    @Autowired
    private ExcelXlsxView excelXlsxView;

    @Autowired
    private ExcelXlsxStreamingView excelXlsxStreamingView;

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:9080", "https://localhost"
                        , "http://WAS1:8080", "http://WAS1:8081", "http://WAS2:8080", "http://WAS2:8081")  // 허용할 주소 및 포트
                .allowedMethods("*");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(excelXlsxView, excelXlsxStreamingView);
    }


}
