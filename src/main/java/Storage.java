import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        try {
            createFolder();
            createFile();
        } catch (IOException e) {
            throw new DukeException("Error creating file");
        }
    }

    private void createFolder() throws IOException {
        Path path = Paths.get(filePath).getParent();
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    private void createFile() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                tasks.add(Task.fromString(line));
            }
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toString());
        }
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
        }
        Files.write(path, lines);
    }
}