package com.example.mylogin.sys.config;

import java.io.File;

public class FileUtil {

    public static String getUniqueFileName(String basePath, String fileName) {
        String uniqueFileName = fileName;
        int count = 0;

        File file = new File(basePath, uniqueFileName);
        while (file.exists()) {
            count++;
            int extensionIndex = fileName.lastIndexOf('.');
            String nameWithoutExtension = (extensionIndex == -1) ? fileName : fileName.substring(0, extensionIndex);
            String extension = (extensionIndex == -1) ? "" : fileName.substring(extensionIndex);
            uniqueFileName = nameWithoutExtension + "_" + count + extension;
            file = new File(basePath, uniqueFileName);
        }

        return uniqueFileName;
    }
}