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
                Task task;
                String[] storedTask = sc.nextLine().split(" \\| ");
                switch (storedTask[0]) {
                case "T":
                    task = new Todo(storedTask[2], storedTask[1].equals("1"));
                    break;
                case "D":
                    task = new Deadline(storedTask[2], storedTask[3], storedTask[1].equals("1"));
                    break;
                case "E":
                    task = new Event(storedTask[2], storedTask[3], storedTask[4], storedTask[1].equals("1"));
                    break;
                default:
                    assert false : storedTask[0];
                    throw new ToothlessException("File corrupted O_O. Try again later.");
                }
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
    public void writeTasks(TaskList tasks){
        try {
            FileWriter writer = new FileWriter(this.filepath);
            for (int i = 0; i < tasks.size(); i++){
                writer.write(tasks.getTask(i).toWrite() + "\n");
            }
            writer.close();
        } catch (IOException e){
            System.err.println("Unable to save task :(");
        }
    }
}
