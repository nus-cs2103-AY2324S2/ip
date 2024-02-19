package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.TaskYapperException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

/**
 * Storage handles the loading and saving of task list data to a file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path to the file used for storing task list data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by filePath.
     * Parses each line of the file into a Task object and adds it to an ArrayList of Tasks.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTasks() throws TaskYapperException {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] inputs = line.split(" \\| ");
                Task task = null;
                switch (inputs[0]) {
                case "T":
                    task = new ToDo(inputs[2]);
                    break;
                case "D":
                    task = new Deadline(inputs[2], inputs[3]);
                    break;
                case "E":
                    task = new Event(inputs[2], inputs[3], inputs[4]);
                    break;
                default:
                    throw new TaskYapperException("Invalid Task, data file may be corrupted");
                }
                if (!task.equals(null)) {
                    if (inputs[1].equals("1")) {
                        task.markDone(true);
                    }
                    taskList.add(task);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            TaskList salvagedTasks = new TaskList(taskList);
            this.saveTasks(salvagedTasks);
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                throw new TaskYapperException("Failed to create a new blank file: " + ioException.getMessage());
            }
            throw new TaskYapperException("Error reading the datafile, it might be corrupted. "
                    + "Creating a new database with any salvaged data");
        }
        return taskList;
    }

    /**
     * Saves the current list of tasks to the file specified by filePath.
     * Converts each task into a format suitable for file storage before writing.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        assert tasks != null;
        try {
            PrintWriter pw = new PrintWriter(filePath);
            for (Task task : tasks.getTasks()) {
                pw.println(task.toFileFormat());
            }
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
