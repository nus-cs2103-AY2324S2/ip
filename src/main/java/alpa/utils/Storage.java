package alpa.utils;

import alpa.exceptions.AlpaException;
import alpa.tasks.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath; 

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryExists();
    }

    private void ensureDirectoryExists() {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public List<Task> loadTasks() throws AlpaException {
        List<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new AlpaException("\nFile not found human..." + e.getMessage());
        }
        return loadedTasks;
    }

    private Task parseLineToTask(String line) throws AlpaException {
        try {
            String[] parts = line.split(" \\| ");

            if (parts.length < 3) {
                throw new AlpaException("\nInvalid line format in tasks file.");
            }

            TaskType taskType = TaskType.fromShortName(parts[0]);
            boolean isDone = "1".equals(parts[1]);
            Task task = null;

            switch (taskType) {
            case TODO:
                task = new ToDo(parts[2]);
                break;
            case DEADLINE:
                LocalDateTime deadlineDateTime = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                task = new Deadline(parts[2], deadlineDateTime);
                break;
            case EVENT:
                String[] dateTimeParts = parts[3].split(" - "); // Assuming parts[3] contains "2022-12-02 14:00 - 2022-12-02 16:00"
                if (dateTimeParts.length != 2) {
                    throw new AlpaException("\nInvalid event time format, expected 'start - end'.");
                }
                LocalDateTime startDateTime = LocalDateTime.parse(dateTimeParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime endDateTime = LocalDateTime.parse(dateTimeParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                task = new Event(parts[2], startDateTime, endDateTime);
                break;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            throw new AlpaException("Error parsing line to task: " + e.getMessage());
        }
    }

    public void saveTasks(List<Task> tasks) throws AlpaException {
        try (FileWriter fw = new FileWriter(filePath);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            for (Task task : tasks) {
                out.println(task.toFileFormat());
            }
        } catch (IOException e) {
            throw new AlpaException("\nError! Could not save tasks!" + e.getMessage());
        }
    }
}