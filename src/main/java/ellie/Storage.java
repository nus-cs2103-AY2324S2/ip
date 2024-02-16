package ellie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ellie.task.Deadline;
import ellie.task.Event;
import ellie.task.Task;
import ellie.task.Todo;

/**
 * The Storage class is responsible for saving tasks to a file and loading tasks from a file.
 * It interacts with the file system to store and retrieve task data.
 */
public class Storage {

    private final String filePath;
    private final File f;

    /**
     * Constructs a Storage object with the specified file path and directory.
     * If the directory does not exist, it creates the directory.
     * If the file does not exist, it creates the file.
     *
     * @param filePath  The path to the file where tasks are stored.
     * @param directory The directory where the file is located.
     */
    public Storage(String filePath, String directory) {
        // Assertion: File path and directory cannot be null
        assert filePath != null && directory != null : "File path or directory is null";

        // If (data) directory does not exist, create directory
        File dir = new File(directory);
        if (!dir.exists()) {
            boolean dirCreated = dir.mkdir();
            if (dirCreated) {
                System.out.println(directory + " Directory created");
            }
        }

        // Recommended file path: "./data/toDoList.txt"
        this.filePath = filePath;
        f = new File(filePath);

        // If file does not exist, create the file
        if (!f.exists()) {
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.write("");
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the file into the provided task list.
     *
     * @param taskList The ArrayList to which loaded tasks will be added.
     */
    public void load(ArrayList<Task> taskList) {
        try {
            Task task;
            Scanner scanner = new Scanner(f);
            if (!scanner.hasNext()) {
                System.out.println("No data loaded");
                return;
            }

            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split("\\|");

                // Assertion: Task parts must have correct format
                assert parts.length >= 3 : "Invalid task format";

                switch(parts[0].charAt(0)) {
                case 'T':
                    task = new Todo(parts[2], Integer.parseInt(parts[1]));
                    taskList.add(task);
                    break;
                case 'D':
                    task = new Deadline(parts[2], Integer.parseInt(parts[1]), parts[3]);
                    taskList.add(task);
                    break;
                case 'E':
                    task = new Event(parts[2], Integer.parseInt(parts[1]), parts[3], parts[4]);
                    taskList.add(task);
                    break;
                default:
                    System.out.println("Invalid task type");
                    break;
                }
            }
            System.out.println("Data loaded");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves tasks from the provided task list to the file.
     *
     * @param taskList The ArrayList containing tasks to be saved.
     */
    public void save(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return;
        }

        // Assertion: File must exist before saving tasks
        assert f.exists() : "File does not exist";

        // Save tasks in txt file.
        try {
            FileWriter fw = new FileWriter(f);

            // type |   1/0  | <event-name> | <date> | <date>
            for (Task element : taskList) {
                fw.write(element.getTaskType());
                fw.write('|' + Integer.toString(element.getIsDone()));
                fw.write('|' + element.toString());

                // add dates, if relevant
                if (element instanceof Deadline) {
                    Deadline d = (Deadline) element;
                    fw.write('|' + d.getDueDate());
                }

                if (element instanceof Event) {
                    Event e = (Event) element;
                    fw.write('|' + e.getStartDate() + '|' + e.getEndDate());
                }

                fw.write(System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Format of toDoList.txt: Each line represents a new todo
    // type |   1/0 for marked status | <event-name> |
    //  T   |   1/0   | <event-name>
    //  D   |   1/0   | <event-name>  | <date>
    //  E   |   1/0   | <event-name>  | <start-date> | <end-date>
}
