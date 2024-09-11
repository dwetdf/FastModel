package com.example.mylogin.sys.service.Embedded;

import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Select {

    public static String process(MultipartFile file1, MultipartFile file2) throws IOException {
        byte[] imageData1 = file1.getBytes();
        byte[] imageData2 = file2.getBytes();

        // 将图像数据转换为Mat对象
        Mat img1 = Imgcodecs.imdecode(new MatOfByte(imageData1), Imgcodecs.IMREAD_COLOR);
        Mat img2 = Imgcodecs.imdecode(new MatOfByte(imageData2), Imgcodecs.IMREAD_COLOR);

        if (img1.empty() || img2.empty()) {
            System.out.println("Error: Images could not be loaded.");
            return null;
        }

        String OutPutPath = "C:\\Graph\\Select\\output.jpg";
        ORB orb = ORB.create();
        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        orb.detect(img1, keypoints1);
        orb.detect(img2, keypoints2);

        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();
        orb.compute(img1, keypoints1, descriptors1);
        orb.compute(img2, keypoints2, descriptors2);

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(descriptors1, descriptors2, matches);

        List<DMatch> matchesList = matches.toList();

        double maxDist = 0;
        double minDist = 100;
        for (int i = 0; i < descriptors1.rows(); i++) {
            double dist = matchesList.get(i).distance;
            if (dist < minDist) minDist = dist;
            if (dist > maxDist) maxDist = dist;
        }

        LinkedList<DMatch> goodMatches = new LinkedList<>();
        for (int i = 0; i < descriptors1.rows(); i++) {
            if (matchesList.get(i).distance <= Math.max(2 * minDist, 30.0)) {
                goodMatches.addLast(matchesList.get(i));
            }
        }

        Mat imgGoodMatches = new Mat();
        Features2d.drawMatches(img1, keypoints1, img2, keypoints2, new MatOfDMatch(goodMatches.toArray(new DMatch[0])), imgGoodMatches, Scalar.all(-1), Scalar.all(-1), new MatOfByte(), Features2d.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS);

        Imgcodecs.imwrite(OutPutPath, imgGoodMatches);

        //HighGui.imshow("所有匹配点对", imgMatches);
        //HighGui.imshow("优化后匹配点对", imgGoodMatches);
        //HighGui.waitKey(0);
        return OutPutPath;
    }

}
