package adam;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import adam.task.Deadline;
import adam.task.Event;
import adam.task.Task;
import adam.task.TaskList;
import adam.task.Todo;

/**
 * Loads and saves the list of tasks to a file.
 */
public class Storage {
    private String filepath;

    /**
     * Returns an instance of a Storage object that will load/save to the given filepath.
     *
     * @param filepath The filepath of the file to load/saves the tasks from/to.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns the list of tasks loaded from the storage file.
     *
     * @return The list of tasks loaded in an ArrayList.
     * @throws AdamException If the file to load from cannot be loaded.
     */
    public ArrayList<Task> load() throws AdamException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Splits the line into details of the task
                String[] taskSymbols = line.split(",");
                Task t;
                switch (taskSymbols[0]) {
                case "T":
                    t = new Todo(taskSymbols[2]);
                    break;
                case "D":
                    t = new Deadline(taskSymbols[2], taskSymbols[3]);
                    break;
                case "E":
                    t = new Event(taskSymbols[2], taskSymbols[3], taskSymbols[4]);
                    break;
                default:
                    throw new AdamException("Unknown task type in storage");
                }
                if (taskSymbols[1].equals("1")) {
                    t.mark();
                }
                tasks.add(t);
            }

            fileScanner.close();
        } catch (IOException e) {
            throw new AdamException("Unable to load file");
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param taskList The list of tasks to save.
     * @throws AdamException If the file cannot be saved to.
     */
    public void save(TaskList taskList) throws AdamException {
        try {
            ArrayList<Task> tasks = taskList.getTasks();

            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("created file");
            }

            PrintWriter writer = new PrintWriter(file);
            for (Task t: tasks) {
                writer.println(t.toFileString());
            }

            writer.close();
        } catch (IOException e) {
            throw new AdamException("Unable to save file");
        }
    }
}
