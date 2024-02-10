package seedu.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(TaskList tasks) {
        this.tasks = tasks.getTasks();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Removes a seedu.task from the list and print out the details.
     *
     * @param idx seedu.task number
     * @return message to be printed by ui
     */
    public String deleteTask(int idx) {
        int taskCount = this.getSize() - 1;
        String message = "Oink! Yosh I have removed this seedu.task:\n"
                + " >> " + this.getTask(idx) + "\nOink's seedu.task count: " + taskCount + "\n";
        this.tasks.remove(idx);
        return message;
    }

    /**
     * Adds a seedu.task to the list and print out the details.
     *
     * @param task new seedu.task to be added
     * @return message to be printed by ui
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "Oink! Nice I have added this seedu.task:\n"
                + " >> " + task + "\nOink's seedu.task count: " + this.getSize() + "\n";
    }

    /**
     * Marks the seedu.task done and prints out that the user has completed the seedu.task.
     *
     * @param idx seedu.task number
     * @return message to be printed by ui
     */
    public String markTask(int idx) {
        this.getTask(idx).markDone();
        return "Oink! You have completed this seedu.task! Nice nice nice\n"
                + " >> " + this.getTask(idx) + "\n";
    }

    /**
     * Marks the seedu.task done and prints out that the user has completed the seedu.task.
     *
     * @param idx seedu.task number
     * @return message to be printed by ui
     */
    public String unmarkTask(int idx) {
        this.getTask(idx).markUndone();
        return "Oink! You have unmarked this seedu.task! Why why why\n"
                + " >> " + this.getTask(idx) + "\n";
    }
}
