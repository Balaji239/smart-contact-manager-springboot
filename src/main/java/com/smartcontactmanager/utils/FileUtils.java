package com.smartcontactmanager.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtils {

    public void saveImage(MultipartFile multipartFile){
        try {
            File file = new File("H:\\Coding\\Dev\\Java\\Spring\\Spring Boot\\SmartContactManagerApp\\src\\main\\resources\\static\\image\\uploads");
            Path path = Paths.get(file.getAbsolutePath()+File.separator+multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            saveToTargetFolder(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToTargetFolder(MultipartFile multipartFile){
        try {
            File file = new ClassPathResource("static/image/uploads/").getFile();
            Path path = Paths.get(file.getAbsolutePath()+File.separator+multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
