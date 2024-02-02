package kitchensink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import kitchensink.task.Deadline;
import kitchensink.task.Event;
import kitchensink.task.Task;
import kitchensink.task.ToDo;

public class Storage {
    private String fileName;

    public Storage(String fileName) throws IOException {
        this.fileName = fileName;
        File f = new File(fileName);
        if (!f.exists()) {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        }
    }

    public void saveTasks(List taskList) throws IOException {
        String newFileContent = taskList.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/duke.txt"));
        writer.write(newFileContent);
        writer.close();
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"));
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            String taskType = currLine.split("]")[0].split("\\[")[1];
            boolean isDone = Objects.equals(currLine.split("\\[")[2].split("]")[0], "X");
            switch (taskType) {
            case "T":
                String description = currLine.split("] ")[1];
                tasks.add(new ToDo(description));
                break;
            case "D":
                description = currLine.split(" \\(by:")[0].split("] ")[1];
                String dueDate = currLine.split("\\(by: ")[1].split("\\)")[0];
                tasks.add(new Deadline(description, LocalDateTime.parse(dueDate,
                        DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))));
                break;
            case "E":
                description = currLine.split(" \\(from:")[0].split("] ")[1];
                String startDate = currLine.split("\\(from: ")[1].split(" to:")[0];
                String endDate = currLine.split("to: ")[1].split("\\)")[0];
                tasks.add(new Event(description, LocalDateTime.parse(startDate,
                        DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                        LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))));
                break;
            default:
            }
            if (isDone) {
                tasks.get(tasks.size() - 1).setToDone();
            }
        }
        return tasks;
    }
}
