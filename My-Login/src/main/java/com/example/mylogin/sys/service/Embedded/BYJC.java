package com.example.mylogin.sys.service.Embedded;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class BYJC {

    public static String process(MultipartFile file) throws IOException {
        String img2Path = "C:\\Graph\\BYJC\\OutPut_img.jpg";
        byte[] imageData1 = file.getBytes();
        Mat img1 = Imgcodecs.imdecode(new MatOfByte(imageData1), Imgcodecs.IMREAD_COLOR); // Read grayscale image
        if (img1.empty()) {
            System.out.println("Error reading the image");
            return null;
        }

        Mat img2 = new Mat(img1.size(), img1.type());

        // OpenCV's Canny edge detection function
        Imgproc.Canny(img1, img2, 50, 150);

        // Save the second image
        Imgcodecs.imwrite(img2Path, img2);
        System.out.println("Image 2 saved to: " + img2Path);

        return img2Path;
    }
}
