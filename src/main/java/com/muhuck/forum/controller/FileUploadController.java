package com.muhuck.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String upload(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException {
//        获取文件的原始名称
        String filename = photo.getOriginalFilename();
//        获取文件类型
        String fileType = photo.getContentType();
//        动态获取web服务器运行路径，并拼接自己的文件存储目录
        String path = request.getServletContext().getRealPath("/upload/");
        saveFile(photo, path);
        System.out.println("path: "+ path);
        return "上传成功";
    }
    public void saveFile(MultipartFile photo, String path) throws IOException {
//        判断存储目录是否存在，如果不存在则创建
        File dir = new File(path);
        if (!dir.exists()) {
//            创建目录
            boolean d = dir.mkdir();
            System.out.println("d+"+d);
        }
//        路径为 存储路径+文件原始名称
        File file1 = new File(path + photo.getOriginalFilename());
//        把网络文件自动存储致此目录中
        photo.transferTo(file1);
    }
}
