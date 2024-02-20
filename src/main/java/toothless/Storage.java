package toothless;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import toothless.tasks.Task;

/**
 * Represents the storage of tasks in a file.
 * This class is responsible for loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    private String filepath;
    private File file;

    /**
     * Constructs a Storage object associated with the filepath.
     * @param filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the storage file and returns them as a List of Task.
     * If the file does not exist, it attempts to create the necessary directory and returns an empty List of Task.
     * @return a list of Tasks loaded from the file.
     * @throws ToothlessException if the file format is corrupted or cannot be parsed correctly.
     */
    public List<Task> load() throws ToothlessException {
        try {
            List<Task> list = new ArrayList<>();
            this.file = new File(this.filepath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] storedTask = sc.nextLine().split(" \\| ");
                Task task = Parser.parseTask(storedTask);
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException exception) {
            new File(this.filepath).getParentFile().mkdirs();
            return new ArrayList<>();
        }
    }

    /**
     * Writes a list of tasks to the storage file
     * by converting each Task into a string format suitable for storage.
     * If the file cannot be written to, an error message is printed.
     * @param tasks the TaskList containing tasks to be saved to the file.
     */
    public void writeTasks(TaskList tasks) throws ToothlessException {
        try {
            FileWriter writer = new FileWriter(this.filepath);
            for (int i = 0; i < tasks.size(); i++){
                Task task = tasks.getTask(i);
                writer.write(task.toWrite() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new ToothlessException("Toothless cannot write to file :(");
        }
    }
}
