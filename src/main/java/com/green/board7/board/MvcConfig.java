package com.green.board7.board;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("D:/download/")
    private String fileDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        exposeDirectory(fileDir, registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        if (dirName.startsWith("../")) dirName = dirName.replace("../", ""); // 상대경로를 절대경로 로 바꿔주는 소스임
        registry.addResourceHandler("/images/**").addResourceLocations("file:/"+uploadPath+"/");
    }
}
