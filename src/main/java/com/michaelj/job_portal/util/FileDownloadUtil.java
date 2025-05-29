package com.michaelj.job_portal.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {
    private Path foundPath;

    public Resource getFileAsResource(String downloadDir, String fileName) throws IOException {
        Path downloadPath = Paths.get(downloadDir);
        Files.list(downloadPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileName)) {
                foundPath = file;
            }
        });

        if (foundPath != null) {
            return new UrlResource(foundPath.toUri());
        }

        return null;
    }
}
