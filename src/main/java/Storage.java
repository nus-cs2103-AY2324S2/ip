import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        Path path = Paths.get(this.filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(path);
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            try {
                Task task = Parser.parseLineToTask(line);
                tasks.add(task);
            } catch (DukeException e) {
                System.out.println("An error occurred while loading tasks: " + e.getMessage());
            }
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        List<String> lines = tasks.stream()
                                  .map(Task::toFileFormat)
                                  .collect(Collectors.toList());
        Path path = Paths.get(this.filePath);
        Files.write(path, lines);
    }
}
