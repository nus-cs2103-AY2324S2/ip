package slaybot;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import entity.Task;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String PATH = "./slaybot.txt";

    /**
     * Constructor for Storage class
     */
    public Storage() {
        try {
            Path filePath = Paths.get(PATH);

            Files.createDirectories(filePath.getParent());

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Saves the tasks from the provided TaskList to a file.
     * <p>
     * This method takes a TaskList object, retrieves the list of tasks from it, and
     * writes each Task to a file in the PATH
     * The tasks are saved in a format suitable for later retrieval.
     *
     * @param tasks The TaskList object which contains the Task objects to be saved
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void saveTasks(TaskList tasks) {
        List<Task> list = tasks.getTasks();

        try {
            FileWriter fw = new FileWriter(PATH);
            for (Task t : list) {
                fw.write(t.save() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
