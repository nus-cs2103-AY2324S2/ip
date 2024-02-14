package duke.fileUtils;

import duke.exceptions.StorageException;

import java.io.*;
import java.net.URL;

public class FileUtil {
    public static void displayFile(String filePath) throws StorageException {
        StringBuilder contentBuilder = new StringBuilder();
        try (InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException | NullPointerException e) {
            throw new StorageException();
        }

        System.out.println(contentBuilder);
    }


    public static String getResourcePath(String filePath) throws IOException {
        URL url = getResource(filePath);
        if (url != null) {
            return url.getPath();
        } else {
            throw new IOException("File not found: " + filePath);
        }
    }

    private static URL getResource(String path) {
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        return classLoader.getResource(path);
    }
}
