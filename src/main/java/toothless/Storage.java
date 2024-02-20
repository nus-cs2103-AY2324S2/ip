package toothless;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import toothless.tasks.Deadline;
import toothless.tasks.Event;
import toothless.tasks.Task;
import toothless.tasks.Todo;

/**
 * Handles loading and storing tasks.
 * This class is responsible for reading tasks from a specific file
 * and writing tasks back to the file.
 * The tasks are stored in a specific format that allows them to be
 * easily parsed and reconstructed into their respective tasks.
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
                Task task = parseTask(storedTask);
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException exception) {
            new File(this.filepath).getParentFile().mkdirs();
            return new ArrayList<>();
        }
    }

    private Task parseTask(String[] storedTask) throws ToothlessException {
        String taskType = storedTask[0];
        String description = storedTask[2];
        boolean isDone = storedTask[1].equals("1");
        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            String date = storedTask[3];
            return new Deadline(description, date, isDone);
        case "E":
            String startDate = storedTask[3];
            String endDate = storedTask[4];
            return new Event(description, startDate, endDate, isDone);
        default:
            throw new ToothlessException("File corrupted O_O. Try again later.");
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
