package floofy;

import floofy.task.Deadline;
import floofy.task.Event;
import floofy.task.Task;
import floofy.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            String[] taskList = tasks.getTaskList();
            for (String task : taskList) {
                writer.write(task + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasks(TaskList list) throws FloofyException{
        try {
            File file = new File("./data/floofy.txt");
            // create parent directory if it doesn't exist.
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    System.err.println("Failed to create parent directories.");
                    return;
                }
            }

            // create file if it doesn't exist.
            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Failed to create the file.");
                return;
            }
            Scanner scanner = new Scanner(file);
            // these are for existing tasks !!
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }
                Task task = parseTask(line);
                if (task != null) {
                    list.addTask(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("An error occurred while loading tasks from file.");
        }
    }
    public Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;

        switch (parts[0]) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            LocalDate deadlineBy = convertDate(parts[3]);
            task = new Deadline(parts[2], deadlineBy);
            break;
        case "E":
            String[] time = parts[3].split("\\s*-\\s*");
            LocalDate eventFrom = convertDate(time[0]);
            LocalDate eventTo = convertDate(time[1]);
            task = new Event(parts[2], eventFrom, eventTo);
        }
        if (task != null && parts[1].equals("1")) {
            task.markTask();
        }
        return task;
    }

    public static LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        return LocalDate.parse(date, formatter);
    }
}
