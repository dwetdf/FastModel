package com.example.mylogin.sys.controller;

import com.example.mylogin.sys.service.Embedded.AITest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test3")
public class AITestController {
    @PostMapping("/Yolo")
    public Result<String> YoloById(@RequestParam("id") int id,
                                   @RequestParam("videoFile") MultipartFile file) throws IOException {
        String Yolo = AITest.YoloById(id, file);
        byte[] videoData = Files.readAllBytes(Paths.get(Yolo));

        // 对视频文件内容进行Base64编码
        String URL = Base64.getEncoder().encodeToString(videoData);
        return Result.success(URL, "success");

    }

    @PostMapping("/Select")
    public Result<String> SelectById(@RequestParam("id") int id,
                                     @RequestParam("imageFile1") MultipartFile file1,
                                     @RequestParam("imageFile2") MultipartFile file2) throws IOException {
        String Select = AITest.SelectById(id,file1,file2);
        String URL = convertImageToDataURL(Select);
        return Result.success(URL, "success");
    }
    @PostMapping("/YoloA")
    public Result<Map<String, Object>> Yolo(@RequestParam("value") String value,
                                            @RequestParam("videoFile") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String Yolo = AITest.Yolo(value,file);
        byte[] videoData = Files.readAllBytes(Paths.get(Yolo));
        // 对视频文件内容进行Base64编码
        String URL = Base64.getEncoder().encodeToString(videoData);
        map.put("id",2);
        map.put("file",URL);
        return Result.success(map, "success");
    }

    @PostMapping("/SelectA")
    public Result<Map<String, Object>> Select(@RequestParam("value") String value,
                                 @RequestParam("imageFile1") MultipartFile file1,
                                 @RequestParam("imageFile2") MultipartFile file2) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String Select = AITest.Select(value,file1,file2);
        String URL = convertImageToDataURL(Select);
        map.put("id",3);
        map.put("file",URL);
        return Result.success(map, "success");
    }
    private String convertImageToDataURL(String path) {
        try {
            // 读取图片文件的字节数组
            byte[] imageBytes = Files.readAllBytes(Paths.get(path));

            // 将字节数组转换为 Base64 编码的字符串
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // 构建 Data URL
            return "data:image/png;base64," + base64Image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}