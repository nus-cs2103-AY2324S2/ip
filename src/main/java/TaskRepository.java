import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TaskRepository {
    private static final String FILE_PATH = "./data/taskStorage.txt";

    public static void saveTasks(TaskList taskList) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String task : taskList.listTasks()) {
            lines.add(task);
        }
        Path filePath = Paths.get(FILE_PATH);
        // making the parent directory if it does not exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        } else if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        // saving data to file
        // its overwritten every time
        Files.write(filePath, lines);
    }

    public static TaskList loadTasks() throws IOException {
        TaskList taskList = new TaskList();
        Path filePath = Paths.get(FILE_PATH);
        if (Files.exists(filePath)) {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                taskList.parseTaskFromString(line);
            }
        }
        return taskList;
    }
}