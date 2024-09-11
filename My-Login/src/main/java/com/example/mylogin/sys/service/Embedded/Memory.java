package com.example.mylogin.sys.service.Embedded;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Memory {
    public static double getMemUsage() {
        /*
        Map<String, Object> MemUsage = new HashMap<>();
        for(int i = 1; i<=4; i++){
            MemUsage.put(String.valueOf(i),getMemUsageById(i));
        }
        return MemUsage;
        */
        Random random = new Random();
        int min = 10;
        int max = 90;

        return random.nextInt(max - min + 1) + min;
    }

    public static double getTotalMem() {
        /*
        Map<String, Object> TotalMem = new HashMap<>();
        for(int i = 1; i<=4; i++){
            TotalMem.put(String.valueOf(i),getTotalMemById(i));
        }
        return TotalMem;
        */
        Random random = new Random();
        int min = 1500;
        int max = 3000;

        return random.nextInt(max - min + 1) + min;
    }

    public static double getLastMem() {
        /*
        Map<String, Object> LastMem = new HashMap<>();
        for(int i = 1; i<=4; i++){
            LastMem.put(String.valueOf(i),getLastMemById(i));
        }
        return LastMem;
        */
        Random random = new Random();
        int min = 500;
        int max = 1000;

        return random.nextInt(max - min + 1) + min;
    }

    public static double getMemUsageById(int id) {
        if(id == 1 || id ==2 || id==3 || id==4 )
        {
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
            int max = 900;

            return random.nextInt(max - min + 1) + min;
        }
        else return -1;
    }

    public static double getTotalMemById(int id) {
        if(id == 1 || id ==2 || id==3 || id==4 )
        {
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
            int min = 1500;
            int max = 3000;

            return random.nextInt(max - min + 1) + min;}
        else return -1;
    }

    public static double getLastMemById(int id) {
        if(id == 1 || id ==2 || id==3 || id==4 )
        {
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
            int min = 500;
            int max = 1000;

            return random.nextInt(max - min + 1) + min;}
        else return -1;
    }
}
//模拟返回值
//此Java类使用接口调用嵌入式设备程序
