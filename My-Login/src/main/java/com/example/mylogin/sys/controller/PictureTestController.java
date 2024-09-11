package com.example.mylogin.sys.controller;

import com.example.mylogin.sys.service.Embedded.ImageTest;
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
@RequestMapping("/test2")
public class PictureTestController {
    @PostMapping("/Gauss")
    public Result<String> GaussById(@RequestParam("id") int id,
                                    @RequestParam("imageFile") MultipartFile file) throws IOException {
        String Gauss = ImageTest.GaussById(id,file);
        String URL = convertImageToDataURL(Gauss);
        return Result.success(URL, "success");
    }

    @PostMapping("/BYJC")
    public Result<String> BYJCById(@RequestParam("id") int id,
                                   @RequestParam("imageFile") MultipartFile file) throws IOException {
        String BYJC = ImageTest.BYJCById(id,file);
        String URL = convertImageToDataURL(BYJC);
        return Result.success(URL, "success");
    }
    @PostMapping("/GaussA")
    public Result<Map<String, Object>> Gauss(@RequestParam("value") String value,
                                @RequestParam("imageFile") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String Gauss = ImageTest.Gauss(value,file);
        String URL = convertImageToDataURL(Gauss);
        map.put("id",2);
        map.put("file",URL);
        return Result.success(map, "success");
    }

    @PostMapping("/BYJCA")
    public Result<Map<String, Object>> BYJC(@RequestParam("value") String value,
                                            @RequestParam("imageFile") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String BYJC = ImageTest.BYJC(value,file);
        String URL = convertImageToDataURL(BYJC);
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

