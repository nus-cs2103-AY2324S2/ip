package adam;

import adam.task.Deadline;
import adam.task.Event;
import adam.task.Task;
import adam.task.TaskList;
import adam.task.Todo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
     * Returns an instance of a Storage object that will load/save to the default filepath "./adam.txt".
     */
    public Storage() {
        this("./adam.txt");
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
                String[] task = line.split("," );
                Task t = null;
                switch (task[0]) {
                    case "T":
                        t = new Todo(task[2]);
                        break;
                    case "D":
                        t = new Deadline(task[2], task[3]);
                        break;
                    case "E":
                        t = new Event(task[2], task[3], task[4]);
                        break;
                }
                if (task[1].equals("1")) {
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
     * @param tasklist The list of tasks to save.
     * @throws AdamException If the file cannot be saved to.
     */
    public void save(TaskList tasklist) throws AdamException {
        try {
            ArrayList<Task> tasks = tasklist.getTasks();
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
