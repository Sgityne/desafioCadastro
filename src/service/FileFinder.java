package service;

import java.nio.file.Path;
import java.util.List;

public class FileFinder {
    public static Path parseFileName(List<Path> paths, String name) {
        String normalizedName = name.replaceAll("\\s", "").toUpperCase();
        for (Path path : paths) {
            String fileName = path.getFileName().toString().split("-")[1].split("\\W")[0];
            if (fileName.equals(normalizedName)) {
                return path;
            }
        }
        return null;
    }
}
