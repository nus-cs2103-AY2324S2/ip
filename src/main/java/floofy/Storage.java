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

/**
 * Represents the storage class to handle the loading and saving of tasks.
 */
public class Storage {

    /** The file path of the file to store the tasks. */
    private final String filePath;

    /**
     * Constructs a Storage object with the file path of the file to store the tasks.
     *
     * @param filePath The file path of the file to store the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The task list to be saved.
     */
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

    /**
     * Loads the tasks from the file.
     *
     * @param list The task list to be loaded.
     * @throws FloofyException If an error occurs while loading tasks from file.
     */
    public void loadTasks(TaskList list) throws FloofyException {
        try {
            File file = new File("./data/floofy.txt");
            // create parent directory if it doesn't exist.
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new FloofyException("Directory could not be created :(");
                }
            }

            // create file if it doesn't exist.
            if (!file.exists() && !file.createNewFile()) {
                throw new FloofyException("File could not be created :(");
            }

            Scanner scanner = new Scanner(file);
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
        } catch (FloofyException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file.");
        }
    }

    /**
     * Parses the task from the file.
     *
     * @param line The line to be parsed.
     * @return The task parsed from the line.
     */
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

    /**
     * Converts the date from the file to a LocalDate object.
     *
     * @param date The date to be converted.
     * @return The LocalDate object converted from the date.
     */
    public static LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        return LocalDate.parse(date, formatter);
    }
}
