package com.example.mylogin.sys.controller;

import com.example.mylogin.sys.service.Embedded.SignalTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test1")
public class SignalTestController {
    @PostMapping("/FFT")
    public Result<String> FFTById(@RequestParam("id") int id,
                                  @RequestParam("selectedFile") MultipartFile file) throws IOException {
        String FFT = SignalTest.FFTById(id, file);
        return Result.success(Resource(FFT), "success");
    }

    @PostMapping("/FIR")
    public Result<String> FIRById(@RequestParam("id") int id,
                                  @RequestParam("selectedFile") MultipartFile file) throws IOException {
        String FIR = SignalTest.FIRById(id,file);
        return Result.success(Resource(FIR), "success");
    }

    @PostMapping("/FFTA")
    public Result<Map<String, Object>> FFT(@RequestParam("value") String value,
                              @RequestParam("selectedFile") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String FFT = SignalTest.FFT(value,file);
        map.put("id",2);
        map.put("file",Resource(FFT));
        return Result.success(map, "success");
    }

    @PostMapping("/FIRA")
    public Result<Map<String, Object>> FIR(@RequestParam("value") String value,
                              @RequestParam("selectedFile") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String FIR = SignalTest.FIR(value,file);
        map.put("id",3);
        map.put("file",Resource(FIR));
        return Result.success(map, "success");
    }

    public String Resource(String S) throws IOException {
        Resource resource = null;
        if (S != null) {
            resource = new ClassPathResource(S);
        }
        String content = null;
        if (resource != null) {
            content = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        }
        return content;
    }

}
