import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static boolean isMarked(String input) {
        return String.valueOf(input.charAt(4)).equals("X");
    }
    public static Path addTasks(ArrayList<Task> storage, Path filePath) throws IOException {
        try {
            List<String> taskList = Files.readAllLines(filePath);
            for (String s : taskList) {
                if (s.startsWith("[T]")) {
                    ToDo task = new ToDo(s.substring(8));
                    if (FileHandler.isMarked(s)) {
                        task.markTask();
                    }
                    storage.add(task);
                } else if (s.startsWith("[D]")) {
                    Deadline task = new Deadline(s.substring(8));
                    if (FileHandler.isMarked(s)) {
                        task.markTask();
                    }
                    storage.add(task);
                } else if (s.startsWith("[E]")) {
                    Event task = new Event(s.substring(8));
                    if (FileHandler.isMarked(s)) {
                        task.markTask();
                    }
                    storage.add(task);
                }
            }
            return filePath;
        } catch (NoSuchFileException e) {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            return Files.createFile(filePath);
        }

    }
}
