package com.example.mylogin.sys.service.Embedded;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class YOLO {

    public static String runYoloDetection(MultipartFile file) throws IOException {
        // 使用系统临时目录
        String uploadDir = System.getProperty("java.io.tmpdir") + File.separator + "yolo" + File.separator;
        String videoOutPath = uploadDir + "output.mp4";

        // 确保上传目录存在
        Path uploadPath = Paths.get(uploadDir);
        Files.createDirectories(uploadPath);

        // 创建目标文件
        String originalFilename = file.getOriginalFilename();
        String videoInPath = uploadDir + (originalFilename != null ? originalFilename : "input.mp4");
        File targetFile = new File(videoInPath);

        System.out.println("Input path: " + videoInPath);
        System.out.println("Output path: " + videoOutPath);

        try {
            // 保存文件
            file.transferTo(targetFile);

            // 使用ProcessBuilder执行Python脚本
            ProcessBuilder pb = new ProcessBuilder(
                    "D:\\anaconda3\\envs\\pytorch\\python.exe",
                    "C:\\yolo\\main.py",
                    videoInPath,
                    videoOutPath
            );

            Process process = pb.start();

            // 读取Python脚本的输出
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Python output: " + line);
                }

                while ((line = errorReader.readLine()) != null) {
                    System.out.println("Python Error: " + line);
                }
            }

            // 等待进程结束
            int exitCode = process.waitFor();
            System.out.println("Python process exit code: " + exitCode);

            if (exitCode != 0) {
                throw new RuntimeException("Python script execution failed with exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to run YOLO detection: " + e.getMessage(), e);
        }

        return videoOutPath;
    }
}