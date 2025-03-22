package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileUtil class provides utility methods for file operations.
 */
public class FileUtil {

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param path The path of the file to be read.
     * @return The content of the file as a string, or null if an error occurs.
     */
    public static String getFile(String path) {
        try {
            if (System.getProperty("user.dir").contains("text-ui-test")) {
                path = "." + path;
            }

            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
