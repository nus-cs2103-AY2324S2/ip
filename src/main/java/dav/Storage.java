package dav;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Storage {

    private static final String FILE_PATH = "./data/dav.txt";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        try {
            List<Task> tasks = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                Task task = Task.parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
    }

    public void save(List<Task> tasks) throws DukeException {
        try {
            Path filePath = Paths.get(FILE_PATH);
            StringBuilder data = new StringBuilder();

            for (Task task : tasks) {
                data.append(task.toDataString()).append("\n");
            }

            Files.write(filePath, data.toString().getBytes());
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
