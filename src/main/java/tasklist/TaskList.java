package tasklist;

import java.util.ArrayList;

import tasks.Task;


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
    @SuppressWarnings("unchecked")
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
     *
     * @return A string representation of the tasks in the task list,
     * formatted with their index, type, status, and description.
     */
    public String displayList() {
        int index = 1;
        StringBuilder list = new StringBuilder();
        for (Task task : this.list) {
            list.append(String.format("%d. [%s] [%s] %s\n", index, task.getTypeIcon(), task.getStatusIcon(),
                    task.toString()));
            index++;
        }
        return list.toString();
    }

    /**
     * Marks a task as done based on its index in the task list.
     *
     * @param index The index of the task to be marked as done.
     * @return A message indicating the result of marking the task as done.
     */
    public String markTask(int index) {
        try {
            return this.list.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            return ("No task at that index!\n");
        }
    }

    /**
     * Unmarks a task as done based on its index in the task list.
     *
     * @param index The index of the task to be unmarked.
     * @return A message indicating the result of unmarking the task.
     */
    public String unmarkTask(int index) {
        try {
            return this.list.get(index - 1).maskAsUndone();
        } catch (IndexOutOfBoundsException e) {
            return ("No task at that index!\n");
        }
    }

    /**
     * Deletes a task based on its index in the task list.
     *
     * @param index The index of the task to be deleted.
     * @return A message indicating the result of deleting the task.
     */
    public String deleteTask(int index) {
        try {
            Task t = this.list.remove(index - 1);
            StringBuilder remark = new StringBuilder();
            remark.append("Noted. The following task is removed!\n");
            remark.append(String.format("[%s] [%s] %s\n", t.getTypeIcon(),
                    t.getStatusIcon(), t.toString()));
            remark.append(String.format("Now you only have %d tasks left. %s\n",
                    this.getListSize(), this.getListUpdate()));
            return remark.toString();
        } catch (IndexOutOfBoundsException e) {
            return ("No task at that index!\n");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     * @return A message indicating the result of adding the task.
     */
    public String addToList(Task t) {
        this.list.add(t);
        return (String.format("Added: %s\n", t.toString()));
    }
}
