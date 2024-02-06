package wis.task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import wis.Storage;
import wis.util.Printer;

/** A list of all tasks. */
public class TaskList {
    private ArrayList<Task> list;
    private int taskCount;

    public TaskList() {
        this.list = new ArrayList<>();
        this.taskCount = 0;
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.taskCount = list.size();
    }

    /**
     * Adds a new task to the task list. Saves the task to hard disk.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.list.add(task);
        this.taskCount++;
        Storage.saveTasks(this);
    }

    /**
     * Peeks a task to the task list given its index. Makes no
     * modification to the task list.
     *
     * @param i Index of task to be peeked (uses 0-indexing).
     */
    public Task get(int i) {
        return this.list.get(i);
    }

    /**
     * Removes a task from the task list given its index.
     *
     * @param i Index of task to be retrieved (uses 0-indexing).
     * @return Task removed.
     */
    public Task remove(int i) {
        this.taskCount--;
        Storage.saveTasks(this);
        return this.list.remove(i);
    }

    public void print() {
        for (int i = 0; i < taskCount; i++) {
            Printer.println((i + 1) + "." + list.get(i).toString());
        }
    }

    public void printTaskCount() {
        Printer.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Feeds all tasks in the task list to a buffered writer.
     *
     * @throws IOException  If fails to write to the buffer writer.
     */
    public void save(BufferedWriter bufferedWriter) throws IOException {
        for (Task task : list) {
            bufferedWriter.write(task.toSavedString());
        }
    }
}
