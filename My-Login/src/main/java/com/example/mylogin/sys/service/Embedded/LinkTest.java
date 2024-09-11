package com.example.mylogin.sys.service.Embedded;

import java.util.*;

public class LinkTest {
    public static List<Map<String, Object>> Heartbeat() {
        List<Map<String, Object>> dataList = new ArrayList<>();

        for (int id = 1; id <= 7; id++) {
            dataList.add(HeartbeatById(id));
        }
        return dataList;

    }
    public static List<Map<String, Object>> TaskComm() {
        List<Map<String, Object>> dataList = new ArrayList<>();

        for (int id = 1; id <= 7; id++) {
            dataList.add(TaskCommById(id));
        }
        return dataList;

    }
    public static List<Map<String, Object>> WebLink() {
        List<Map<String, Object>> dataList = new ArrayList<>();

        for (int id = 1; id <= 7; id++) {
            dataList.add(WebLinkById(id));
        }
        return dataList;

    }
    public static double getBandwidthById(int id) {
        return 18.6;
    }
    public static Map<String, Object> HeartbeatById(int id) {
        Map<String, Object> map = new HashMap<>();
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
        if (id==1||id==2||id==3||id==4||id==5||id==6) {
            map.put("id", id);
            map.put("state", "心跳保持");

        }else {
            map.put("id", 7);
            map.put("state", "心跳停止");
        }
        return map;
    }
    public static Map<String, Object> TaskCommById(int id) {
        Map<String, Object> map = new HashMap<>();
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
        if (id==1||id==2||id==3||id==4||id==5||id==6) {
            map.put("id", id);
            map.put("task", "通信正常");

        }else {
            map.put("id", 7);
            map.put("task", "通信中断");
        }
        return map;
    }
    public static Map<String, Object> WebLinkById(int id) {
        Map<String, Object> map = new HashMap<>();
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
        if (id==1||id==2||id==3||id==4||id==5||id==6) {
            Random random = new Random();
            int min = 10;
            int max = 100;

            int width = random.nextInt(max - min + 1) + min;
            int delay = random.nextInt(max - min + 1) + min;

            String widths = width + " Mbps ";
            String delays = delay + " ms ";

            map.put("id", id);
            map.put("link", "网络连接");
            map.put("bandwidth", widths);
            map.put("delay", delays);
        }else{
            map.put("id", 7);
            map.put("link", "连接中断");
            map.put("bandwidth", "/");
            map.put("delay", "999ms");
        }

        return map;
    }
}
//此Java类使用接口调用嵌入式设备程序