package com.example.mylogin.sys.service.Embedded;

import java.util.*;

public class Electricity {
    public static List<Map<String, Object>> getModule() {
        List<Map<String, Object>> dataList = new ArrayList<>();

        for (int id = 1; id <= 7; id++) {
            dataList.add(getModuleById(id));
        }
        return dataList;
    }
    public static Map<String, Object> getModuleById(int id) {
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
            int miniu = 220;
            int maxiu = 230;
            int minou = 210;
            int maxou = 220;
            int mini = 8;
            int maxi = 12;

            int inU = random.nextInt(maxiu - miniu + 1) + miniu;
            int outU = random.nextInt(maxou - minou + 1) + minou;
            int I = random.nextInt(maxi - mini + 1) + mini;

            int P = ((inU+outU)/2 * I);

            map.put("id", id);
            map.put("inU", inU);
            map.put("outU", outU);
            map.put("I", I);
            map.put("P", P);
            map.put("state", "供电正常");
        }else{
        map.put("id", 7);
        map.put("inU", "126");
        map.put("outU", "/");
        map.put("I", "/");
        map.put("P", "/");
        map.put("state", "供电异常");
        }
    return map;
    }
}
//模拟返回值
//此Java类使用接口调用嵌入式设备程序
