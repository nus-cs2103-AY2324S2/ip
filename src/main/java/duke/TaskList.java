package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

/**
 * TaskList class to handle task operations
 */
public class TaskList {
    private List<Task> internalList;
    private File dataFile;

    /**
     * Constructor for TaskList class
     *
     * @param dataFile File object
     */
    TaskList(File dataFile) {
        this(new ArrayList<>(), dataFile);
    }

    TaskList(List<Task> list, File dataFile) {
        this.internalList = list;
        this.dataFile = dataFile;
    }

    /**
     * Generates a string representation of the current list
     *
     * @return String of tasks
     */
    public String generateName() {
        String s = "";
        for (int i = 0; i < internalList.size(); i++) {
            Task t = internalList.get(i);
            s += String.format("%d. %s", i + 1, t.getName()) + System.lineSeparator();
        }
        return s;
    }

    public Task getTask(int index) {
        return internalList.get(index - 1);
    }

    /**
     * Adds task to list
     *
     * @param t Task to add
     */
    public void add(Task t) {
        this.internalList.add(t);
        this.saveData();
    }

    /**
     * Deletes task from list
     *
     * @param index 1-indexed index of task to delete
     */
    public void delete(int index) {
        internalList.remove(index - 1);
        this.saveData();
    }

    /**
     * Deletes and return task from list
     *
     * @param index 1-indexed index of task to mark
     */
    public Task pop(int index) {
        Task t = this.getTask(index);
        this.delete(index);
        return t;
    }

    private void saveData() {
        ArrayList<String> data = new ArrayList<>();
        for (Task t : this.internalList) {
            data.add(t.exportData());
        }
        String dataString = String.join(System.lineSeparator(), data);
        try (FileWriter writer = new FileWriter(dataFile);) {
            writer.write(dataString);
        } catch (IOException e) {
            System.out.println("Data could not be saved.");
        }
    }

    public String findTasks(String s) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task t : internalList) {
            if (t.name.contains(s)) {
                filtered.add(t);
            }
        }
        return new TaskList(filtered, this.dataFile).generateName();
    }
}
