import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;

public class Storage {
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

    public static String listToString(List<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return sb.toString();
    }

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
