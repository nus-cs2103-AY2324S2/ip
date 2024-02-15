package chimp.core;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;

public class Storage {
    /**
     * Checks if the given command is a save command.
     *
     * @param command the command to be checked
     * @return true if the command is a save command, false otherwise
     */
    public static boolean isSaveCommand(String command) {
        HashSet<String> saveCommands = new HashSet<>(
                List.of(
                        "mark",
                        "unmark",
                        "todo",
                        "event",
                        "deadline",
                        "delete"));
        return saveCommands.contains(command);
    }

    /**
     * Saves the output to a file.
     *
     * @param output the output to be saved
     */
    public static void saveOutputToFile(String output) {
        Path filePath = Path.of("./data/chimp.txt");

        // Create file if it does not exist
        try {
            Path directoryPath = filePath.getParent();
            if (directoryPath != null) {
                Files.createDirectories(directoryPath);
            }
            Files.createFile(filePath);
        } catch (FileAlreadyExistsException e) {
            // Ignore this error
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write to file
        try {
            Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(filePath, output.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
