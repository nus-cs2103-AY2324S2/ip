package UiRelated;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import TaskLists.TaskList;
import Tasks.DeadLineTask;
import Tasks.EventTask;
import Tasks.Task;
import Tasks.ToDoTask;


/**
 * The Storage class handles loading and saving tasks from/to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param input The file path where tasks will be loaded from or saved to.
     */
    public Storage(String input) {
        filePath = input;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws FileNotFoundException If the file specified by the file path is not found.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] components = line.split(Pattern.quote("|"));

            String type = components[0];
            String mark = components[1];
            String taskName = components[2];
            Task task;
            switch (type) {
            case "T":
                task = new ToDoTask(taskName);
                taskList.add(task);
                break;
            case "D":
                task = new DeadLineTask(components[3], taskName);
                taskList.add(task);
                break;
            default:
                task = new EventTask(components[3], components[4], taskName);
                taskList.add(task);
            }
            if (mark.equals("Y")) {
                task.markAsDone();
            }
        }
        scanner.close();
        return taskList;
    }

    /**
     * Saves tasks to the file specified by the file path.
     *
     * @param taskList The TaskList containing tasks to be saved to the filePath.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(task.logString());
                writer.newLine();
            }
        }
    }

}

