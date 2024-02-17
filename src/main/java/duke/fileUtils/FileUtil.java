package duke.fileUtils;

import duke.exceptions.StorageException;

import java.io.*;
import java.net.URL;
/**
 * The FileUtil class provides methods to display the content of a file.
 * It handles file I/O operations and interacts with the Ui to display the content of a file.
 * <p>
 * The class provides methods to display the content of a file.
 * </p>
 * <p>
 * The file path for the file to be displayed is provided during method call.
 * </p>
 *
 * @version 1.0
 * @see duke.mainUtils.Ui
 * @see duke.exceptions.StorageException
 */
public class FileUtil {
    /**
     * Displays the content of a file.
     *
     * @param filePath the file path of the file to display.
     * @throws StorageException if there is an error displaying the file.
     */
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
    /**
     * Retrieves the URL of a file.
     *
     * @param path the file path of the file to retrieve.
     * @return the URL of the file.
     */
    private static URL getResource(String path) {
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        return classLoader.getResource(path);
    }
}
