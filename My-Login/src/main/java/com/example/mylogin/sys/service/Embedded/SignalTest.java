package com.example.mylogin.sys.service.Embedded;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class SignalTest {
    public static String FFTById(int id, MultipartFile file) {
        if( id == 1 || id ==2 || id==3 || id==4 ) {
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
            FFT.processFFT(file);
            return FFT.outputToFile();
        }
        else return null;
    }

    public static String FIRById(int id, MultipartFile file) throws IOException {
        if( id == 1 || id ==2 || id==3 || id==4 ) {
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
            double[] h = FIR.Firls(file);
            return FIR.printFirResponse(h);
        }
        else return null;
    }

    public static String FFT(String value, MultipartFile file) throws IOException {
        /*
        int id = 0;
        if ("1".equals(value)) {
            double[] Usage = new double[7];
            for (int i = 1; i <= 7; i++) {
                Usage[i] = CpuUsage.getCpuUsageById(i);
            }
            double min = 100;
            for (int i = 1; i <= 7; i++) {
                if (Usage[i] < min) {
                    min = Usage[i];
                    id = i;
                }
            }
        } else if ("2".equals(value)) {
            double[] Usage = new double[7];
            for (int i = 1; i <= 7; i++) {
                Usage[i] = Memory.getMemUsageById(i);
            }
            double min = 100;
            for (int i = 1; i <= 7; i++) {
                if (Usage[i] < min) {
                    min = Usage[i];
                    id = i;
                }
            }
        }else if ("3".equals(value)){
            double[] Bandwidth = new double[7];
            for (int i = 1; i <= 7; i++) {
                Bandwidth[i] = LinkTest.getBandwidthById(i);
            }
            double min = 100;
            for (int i = 1; i <= 7; i++) {
                if (Bandwidth[i] < min) {
                    min = Bandwidth[i];
                    id = i;
                }
            }
        }
        return FFTById(id, file);
        */
        FFT.processFFT(file);
        return FFT.outputToFile();
    }

    public static String FIR(String value, MultipartFile file) throws IOException {
        /*
        int id = 0;
        if ("1".equals(value)) {
            double[] Usage = new double[7];
            for (int i = 1; i <= 7; i++) {
                Usage[i] = CpuUsage.getCpuUsageById(i);
            }
            double min = 100;
            for (int i = 1; i <= 7; i++) {
                if (Usage[i] < min) {
                    min = Usage[i];
                    id = i;
                }
            }
        } else if ("2".equals(value)) {
            double[] Usage = new double[7];
            for (int i = 1; i <= 7; i++) {
                Usage[i] = Memory.getMemUsageById(i);
            }
            double min = 100;
            for (int i = 1; i <= 7; i++) {
                if (Usage[i] < min) {
                    min = Usage[i];
                    id = i;
                }
            }
        }else if ("3".equals(value)){
            double[] Bandwidth = new double[7];
            for (int i = 1; i <= 7; i++) {
                Bandwidth[i] = LinkTest.getBandwidthById(i);
            }
            double min = 100;
            for (int i = 1; i <= 7; i++) {
                if (Bandwidth[i] < min) {
                    min = Bandwidth[i];
                    id = i;
                }
            }
        }
        return FIRById(id,file);
        */
        double[] h = FIR.Firls(file);
        return FIR.printFirResponse(h);
    }
}
