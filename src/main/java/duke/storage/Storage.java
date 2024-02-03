package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import duke.tasks.Task;


/**
 * Manages storage operations for Duke application tasks.
 * <p>
 * This class handles the loading, adding, and saving of task objects to a file.
 * It maintains an in-memory list of tasks for quick access and manipulation.
 * </p>
 */
public class Storage {
    private static final String FILEPATH = "../../../data/rah.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    
    public void writeToFile(ArrayList<Task> inventory) throws IOException {
        File file = new File(FILEPATH);
        // Ensure the directory exists
        file.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(file, false)) { // false to overwrite
            for (Task task : inventory) {
                    fw.write(task.toString() + System.lineSeparator());
            }
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Retrieves the current list of tasks.
     * <p>
     * This method returns an ArrayList containing all the tasks currently stored in memory.
     * </p>
     *
     * @return An ArrayList of {@link Task} objects representing the tasks currently stored.
     */
    public ArrayList<Task> load() {
        return tasks;
    }

    @Override
    public String toString() {
        String result = "";
        int count = 1;
        for (Task s : tasks) {
            result += count + ". " + s.toString() + "\n";
            count++;
        }
        return result;
    }
}


