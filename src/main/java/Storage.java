import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Storage {
    private TaskList taskList;
    private Path filePath;

    public Storage(String filePath, TaskList taskList) {
        this.filePath = Paths.get(filePath);
        this.taskList = taskList;
    }

    public void load() {
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    this.taskList.addTask(parseFromFile(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : this.taskList.getList()) {
                writer.write(task.toStringForStore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task parseFromFile(String string) {
        String[] parts = string.split(" \\| ", 3);
        String command = parts[0];
        String isDone = parts[1];
        String content = parts[2];
        Task newTask = null;

        switch (command) {
        case "T":
            newTask = new ToDo(content);
            break;
        case "E":
            String[] eventParts = content.split(" \\| ", 2);
            String description = eventParts[0];
            String datePart = eventParts[1];
            String[] dates = datePart.split(" to ");
            String startDate = dates[0].substring(dates[0].indexOf("from") + 5).trim();
            String endDate = dates[1].trim();
            newTask = new Event(description, startDate, endDate);
            break;
        case "D":
            String[] deadlineParts = content.split(" \\| by");
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        }

        newTask.setStatus((isDone == "1"));
        return newTask;
    }
}

