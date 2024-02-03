import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import task.*;

public class Storage {

    File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    public void saveList(TaskList list) {
        try {
            createFileIfNotExist();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(list.getSavedList());
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving the task list: " + e.getMessage());
        }
    }

    public TaskList loadData() {
        TaskList list = new TaskList();
        try {
            createFileIfNotExist();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                list.addTask(readLine(line));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading the task list: " + e.getMessage());
        }
        return list;
    }

    private Task readLine(String line) {
        String[] taskInfo = line.split(" \\| ");
        Task task = null;
        switch (taskInfo[0]) {
            case "T":
                task = new Todo(taskInfo[2]);
                break;
            case "D":
                try {
                    LocalDateTime deadline = LocalDateTime.parse(taskInfo[3], Task.formatter);
                    task = new Deadline(taskInfo[2], deadline);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid deadline format in the task list: " + e.getMessage());
                }
                break;
            case "E":
                try {
                    LocalDateTime startDateTime = LocalDateTime.parse(taskInfo[3], Task.formatter);
                    LocalDateTime endDateTime = LocalDateTime.parse(taskInfo[4], Task.formatter);
                    task = new Event(taskInfo[2], startDateTime, endDateTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid event format in the task list: " + e.getMessage());
                }
                break;
            default:
                break;
        }
        if (taskInfo[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private void createFileIfNotExist() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating the file: " + e.getMessage());
        }
    }
}