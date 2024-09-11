package com.example.mylogin.sys.service.Embedded;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CpuUsage {

    public static double getCpuUsage() {
        /*
        Map<String, Object> cpu = new HashMap<>();
        for(int i = 1; i<=4; i++){
            cpu.put(String.valueOf(i),getCpuUsageById(i));
        }
        return cpu;
        */
        Random random = new Random();
        int min = 10;
        int max = 90;

        return random.nextInt(max - min + 1) + min;//模拟返回值
    }

    public static double getCpuUsageById(int id) {
        if(id == 1 || id ==2 || id==3 || id==4 ) {
            /*
        String serverAddress = "127.0.0.1";
        int port = 12345; // 嵌入式处理单元监听的端口号

        try (Socket socket = new Socket(serverAddress, port)) {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            // 发送请求
            writer.println("请求硬件状态信息4");

            // 读取响应
            String response = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
            Random random = new Random();
            int min = 10;
            int max = 90;

            return random.nextInt(max - min + 1) + min;
        }
        else return -1;
    }
}
//此Java类使用接口调用嵌入式设备程序