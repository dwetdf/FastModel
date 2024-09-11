package com.example.mylogin.sys.service.Embedded;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class Gauss {

    private static int gKernelSize = 11;
    private static int gXSigma = 10;
    private static int gYSigma = 10;

    static String processImages(MultipartFile imageFile) throws IOException {
        // 从MultipartFile中读取图像数据
        byte[] imageData = imageFile.getBytes();

        // 将图像数据转换为Mat对象
        Mat gImgOri = Imgcodecs.imdecode(new MatOfByte(imageData), Imgcodecs.IMREAD_GRAYSCALE);

        if (gImgOri.empty()) {
            System.out.println("Cannot find original picture!!");
            return null;
        }

        // 创建一个空的目标 Mat 对象
        Mat gImgGaussFilter = new Mat();

        // 使用GaussianBlur进行图像处理
        GaussianBlur(gImgOri, gImgGaussFilter, new Size(gKernelSize, gKernelSize), gXSigma, gYSigma);

        // 保存处理后的图像到指定路径
        return saveImage("C:\\Graph\\Gauss\\output_image.jpg", gImgGaussFilter);
    }

    private static String saveImage(String filePath, Mat image) {
        Imgcodecs.imwrite(filePath, image);
        System.out.println("Image saved to: " + filePath);
        return filePath;
    }

    private static void GaussianBlur(Mat src, Mat dst, Size ksize, double sigmaX, double sigmaY) {
        Imgproc.GaussianBlur(src, dst, ksize, sigmaX, sigmaY);
    }
}