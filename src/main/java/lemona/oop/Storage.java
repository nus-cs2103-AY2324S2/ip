package lemona.oop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

import lemona.task.Task;
import lemona.task.Todo;
import lemona.task.Deadline;
import lemona.task.Event;

/**
 * Represents the storage utility for saving and loading tasks from a file.
 * Storage handles the reading and writing of tasks to and from a specified file.
 */
public class Storage {
    private static String filePath;
    private static final String LINE = "\t______________________________________________________";

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts the list of tasks to a string representation.
     *
     * @param list The list of tasks to be converted to string.
     * @return The string representation of the list of tasks.
     */
    public static String listToString(TaskList list) {
        String content = "";
        for (int i = 0; i < list.size() - 1; i++) {
            Task task = list.get(i);
            content = content + task.getTaskInfo() + "\n";
        }
        Task task = list.get(list.size() - 1);
        content = content + task.getTaskInfo();
        return content;
    }

    /**
     * Converts the string representation of tasks to a list of Task objects.
     *
     * @param tasks The list of tasks to be populated with Task objects.
     * @param text The string representation of tasks.
     * @return The populated list of tasks.
     */
    public static ArrayList<Task> stringToList(ArrayList<Task> tasks, String text) throws DateTimeParseException{
        String[] list = text.split("(new) ");
        for (String s : list) {
            String[] info = s.split("/ ");
            switch (info[0]) {
                case "[T] ":
                    Task task = new Todo(info[2]);
                    if (info[1].equals("[X]")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "[D] ":
                    String dueDate = LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                    task = new Deadline(info[2], dueDate);
                    if (info[1].equals("[X]")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "[E] ":
                    String startTime = LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                    String endTime = LocalDateTime.parse(info[4], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                    task = new Event(info[2], startTime, endTime);
                    if (info[1].equals("[X]")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                default:
            }
        }
        return tasks;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            tasks = stringToList(tasks, task);
        }
        return tasks;
    }

    /**
     * Saves the tasks in the specified task list to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void save(TaskList tasks) throws IOException {
        if (tasks.size() != 0) {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(listToString(tasks));
            fw.close();
        }
    }
}
