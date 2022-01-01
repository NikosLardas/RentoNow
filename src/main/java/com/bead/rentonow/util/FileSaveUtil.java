package com.bead.rentonow.util;

import java.io.*;
import java.nio.file.*;
import org.springframework.web.multipart.MultipartFile;

public class FileSaveUtil {

    public static void saveFile(String saveDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path savePath = Paths.get(saveDir);

        if (!Files.exists(savePath)) {
            Files.createDirectories(savePath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = savePath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}