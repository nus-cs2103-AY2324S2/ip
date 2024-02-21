package irwyn.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class encapsulates the TaskList class.
 * It represents a TaskList to handle task operations.
 *
 * @author Irwyn Liong
 * @version Week-3
 */

public class TaskList {
    private final ArrayList<Task> storage;

    /**
     * Constructor for a TaskList object.
     * Adds the lists of task to storage.
     * If file not found, throws an error for loading the task.
     *
     * @param filePath A file containing stored tasks.
     */
    public TaskList(File filePath) {
        storage = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(filePath);
            while (fileReader.hasNextLine()) {
                Task task = stringToTask(fileReader.nextLine());
                if (task != null) {
                    storage.add(task);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading tasks from file.\n");
        }
    }

    /**
     * This method converts a string into a Task object.
     * The string is expected to be in a specific format, with parts separated by " | ".
     * Depending on the first part of the string, a different type of Task object is created.
     * If the second part of the string is "1", the task is marked as done.
     *
     * @param data The string to be converted into a Task object.
     * @return A Task object that represents the task specified by the input string.
     */
    private Task stringToTask(String data) {
        String[] parts = data.split(" \\| ");
        switch (parts[0]) {
        case "T":
            Task todo = new ToDo(parts[2]);
            if (parts[1].equals("1")) {
                todo.mark();
            }
            return todo;
        case "D":
            Task deadline = new Deadline(parts[2], parts[3]);
            if (parts[1].equals("1")) {
                deadline.mark();
            }
            return deadline;
        case "E":
            Task event = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].equals("1")) {
                event.mark();
            }
            return event;
        default:
            return null;
        }
    }

    /**
     * This method returns the list of tasks.
     * @return A list of Task objects that represents the tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.storage;
    }

    /**
     * This method returns a specific task from the list.
     *
     * @param tasks The index of the task in the list.
     * @return A Task object that represents the task at the specified index.
     */
    public Task getTask(int tasks) {
        return this.storage.get(tasks);
    }

    /**
     * This method adds a task to the list.
     *
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        this.storage.add(task);
    }

    /**
     * This method removes a task from the list.
     *
     * @param task The index of the task in the list.
     * @return A Task object that represents the removed task.
     */
    public Task delete(int task) {
        return this.storage.remove(task);
    }

    /**
     * This method returns the size of the list of tasks.
     *
     * @return An integer that represents the number of tasks in the list.
     */
    public int getTasksSize() {
        return this.storage.size();
    }

    /**
     * This method marks a task as done.
     *
     * @param task The index of the task in the list.
     */
    public void mark(int task) {
        storage.get(task).mark();
    }

    /**
     * This method marks a task as not done.
     *
     * @param task The index of the task in the list.
     */
    public void unmark(int task) {
        storage.get(task).unmark();
    }

    /**
     * Sorts the task list.
     */
    public void sort() {
        Collections.sort(this.storage);
    }
}
