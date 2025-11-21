package com.xenodirt;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static File getFileBesideJar(String filename) {
        try {
            URI jarPath = FileUtils.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI();

            Path jarDirectory = Paths.get(jarPath).getParent();

            return jarDirectory.resolve(filename).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to locate JAR directory: ", e);
        }
    }
}
