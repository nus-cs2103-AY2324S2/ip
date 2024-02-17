package duke.fileUtils;

import duke.exceptions.StorageException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The FileUtil class provides utility methods for file-related operations.
 * It includes methods for displaying the content of a file to the console.
 * <p>
 * Note: The paths provided to the methods are assumed to be relative to the classpath.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.exceptions.StorageException
 */
public class FileUtil {

    /**
     * Displays the content of a file to the console.
     *
     * @param filePath the path to the file to display.
     * @throws StorageException if there is an error accessing or reading the file.
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
}
