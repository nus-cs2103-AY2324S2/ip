package huyang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException, TaskException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseLineToTask(line));
            }
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException, TaskException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        }
    }

    private Task parseLineToTask(String line) throws TaskException {
        String type = line.substring(0, 1);
        switch (type) {
        case "T":
            return ToDo.fromFileFormat(line);
        case "D":
            return Deadline.fromFileFormat(line);
        case "E":
            return Event.fromFileFormat(line);
        default:
            throw new TaskException("Unknown task type in file: " + type);
        }
    }

}
