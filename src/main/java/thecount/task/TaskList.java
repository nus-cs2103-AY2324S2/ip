package thecount.task;

import thecount.ui.PrintList;
import thecount.ui.RemoveFromListReply;
import thecount.ui.Reply;
import thecount.exception.TheCountException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        Reply replyToUser = new PrintList(this.tasks);
        replyToUser.displayMessage();
    }

    /**
     * Retrieves the list of tasks to write to file.
     *
     * @return The list of tasks in string format.
     */
    public String getListToWrite() {
        PrintList replyToUser = new PrintList(this.tasks);
        return replyToUser.getListToWrite();
    }

    /**
     * Marks a task as done.
     *
     * @param i The (index + 1) of the task to mark.
     * @param isAnnounced Specifies if the action should be announced.
     * @throws TheCountException If the task index is invalid.
     */
    public void markTask(int i, boolean isAnnounced) throws TheCountException {
        if (i < 1 || i > tasks.size()) {
            throw new TheCountException("Invalid task number. I can't count that!");
        }
        Task currTask = this.tasks.get(i - 1);
        currTask.markAsDone();
        if (isAnnounced) {
            Reply replyToUser = new Reply("Ah-ah-ah! ONE! I've marked this task as done:\n"
                    + "        " + currTask.toString());
            replyToUser.displayMessage();
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param i The (index + 1) of the task to unmark.
     * @param isAnnounced Specifies if the action should be announced.
     * @throws TheCountException If the task index is invalid.
     */
    public void unmarkTask(int i, boolean isAnnounced) throws TheCountException {
        if (i < 1 || i > tasks.size()) {
            throw new TheCountException("Invalid task number. I can't count that!");
        }
        Task currTask = this.tasks.get(i - 1);
        currTask.unmark();
        if (isAnnounced) {
            Reply replyToUser = new Reply("MINUS ONE! I've marked this task as not done yet:\n"
                    + "        " + currTask.toString());
            replyToUser.displayMessage();
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task to delete.
     * @throws TheCountException If the task index is invalid.
     */
    public void deleteTask(int i) throws TheCountException {
        if (i < 1 || i > tasks.size()) {
            throw new TheCountException("Invalid task number. I can't delete that!");
        }
        Task currTask = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        Reply replyToUser = new RemoveFromListReply(currTask.toString(), this.tasks.size());
        replyToUser.displayMessage();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int length() {
        return this.tasks.size();
    }
}
