package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * The tasks can be added, retrieved, marked as done, and deleted.
 */
public class MyList {
    private List<Task> items;

    /**
     * Constructs an empty list.
     */
    public MyList() {
        this.items = new ArrayList<>();
    }

    /**
     * Constructs a list with the saved list of tasks.
     *
     * @param t The list of tasks saved in the text file.
     */
    public MyList(List<Task> t) {
        this.items = t;
    }

    /**
     * Returns the list of tasks for saving.
     *
     * @return The list of tasks.
     */
    public List<Task> getItemsForSaving() {
        return this.items;
    }


    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     * @return A message indicating the success of the operation.
     */
    public String addItem(Task t) {
        this.items.add(t);
        return "Got it. I've added this task:\n" + t.toString() + "\nNow you have " + this.items.size()
                + " tasks in the list.";
    }

    /**
     * Retrieves a string representation of all the tasks in the list.
     *
     * @return A string representing all the tasks in the list.
     */
    public String getItems() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        int index = 1;

        for (Task task : this.items) {
            String itemString = String.format("%d. %s\n", index, task.toString());
            stringBuilder.append(itemString);
            index++;
        }

        return stringBuilder.toString();
    }

    /**
     * Marks a task as done based on its index in the list.
     *
     * @param index The index of the task to be marked as done.
     * @return A message indicating the success of the operation or if the index is out of bounds.
     */
    public String markTask(int index) {
        if (index < 1 || index > this.items.size()) {
            return "Number is outside length of list";
        } else {
            return "Nice! I've marked this task as done:\n" + this.items.get(index - 1).markAsDone();
        }
    }

    /**
     * Deletes a task based on its index in the list.
     *
     * @param index The index of the task to be deleted.
     * @return A message indicating the success of the operation or if the index is out of bounds.
     */
    public String delete(int index) {
        if (index < 1 || index > this.items.size()) {
            return "Number is outside length of list";
        } else {
            Task t = this.items.remove(index - 1);
            return "Noted. I've removed this task:\n" + t.toString() + "\nNow you have " + this.items.size() + " tasks in the list.";
        }
    }
}
