package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static String getFile(String path) {
        try {
            if (System.getProperty("user.dir").contains("text-ui-test")) {
                path = "." + path;
            }

            return Files.readString(Paths.get(path));

        } catch (IOException e) {
            e.printStackTrace();

            return null;

        }
    }
}
