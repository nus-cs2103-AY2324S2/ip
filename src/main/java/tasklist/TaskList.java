package tasklist;

import tasks.Task;

import java.util.ArrayList;

/**
 * The `TaskList` class represents a list that holds tasks managed by the Duke chatbot.
 * It provides methods for manipulating the task list, such as adding, marking as done,
 * unmarking, deleting tasks, and displaying the tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getListSize() {
        return this.list.size();
    }

    /**
     * Gets a clone of the task list.
     *
     * @return A clone of the task list.
     */
    public ArrayList<Task> getList() {
        return (ArrayList<Task>) this.list.clone();
    }

    /**
     * Gets a message providing an update on the status of the task list.
     *
     * @return A message indicating the status of the task list.
     */
    public String getListUpdate() {
        if (this.getListSize() == 0) {
            return "You have no tasks or upcoming deadlines! Take a breather and relax.";
        }
        if (this.getListSize() <= 5) {
            return "Time for more work and less playing!";
        }
        return "HUMAN! Stop watching YouTube and start doing work!!!";
    }

    /**
     * Displays the tasks in the task list, including their index, type, status, and description.
     */
    public void displayList() {
        int index = 1;
        for (Task task : this.list) {
            System.out.println(String.format("%d. [%s] [%s] %s", index, task.getTypeIcon(), task.getStatusIcon(),
                    task.getDescription()));
            index++;
        }
    }

    /**
     * Marks a task as done based on its index in the task list.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        try {
            this.list.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! You fool!");
        }
    }

    /**
     * Unmarks a task as done based on its index in the task list.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        try {
            this.list.get(index - 1).maskAsUndone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! You fool!");
        }
    }

    /**
     * Deletes a task based on its index in the task list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        try {
            Task t = this.list.remove(index - 1);
            System.out.println("Noted. The following task is removed, you careless human being!");
            System.out.println(String.format("[%s] [%s] %s\n", t.getTypeIcon(), t.getStatusIcon(), t.getDescription()));
            System.out.println(String.format("Now you only have %d tasks left. %s", this.getListSize(), this.getListUpdate()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task at that index! You fool!");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addToList(Task t) {
        this.list.add(t);
        System.out.println(String.format("Added: %s", t.getDescription()));
    }
}
