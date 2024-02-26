package duke.fileUtils;

import duke.exceptions.StorageException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return the content of the file as a String.
     * @throws StorageException if there is an error displaying the file.
     */
    public static String displayFile(String filePath) throws StorageException {
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(filePath));
            // Join all lines into a single string
            return fileContent.stream().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException | NullPointerException e) {
            throw new StorageException();
        }
    }
}
