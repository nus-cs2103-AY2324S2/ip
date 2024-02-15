package thecount.task;

import java.util.ArrayList;

import thecount.exception.TheCountException;
import thecount.ui.PrintList;
import thecount.ui.RemoveFromListReply;
import thecount.ui.Reply;

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
    public String printList() {
        Reply replyToUser = new PrintList(this.tasks);
        return replyToUser.displayMessage();
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
    public String markTask(int i, boolean isAnnounced) throws TheCountException {
        if (i < 1 || i > tasks.size()) {
            throw new TheCountException("Invalid task number. I can't count that!");
        }
        Task currTask = this.tasks.get(i - 1);
        currTask.markAsDone();
        if (isAnnounced) {
            Reply replyToUser = new Reply("Ah-ah-ah! ONE! I've marked this task as done:\n"
                    + "" + currTask.toString());
            return replyToUser.displayMessage();
        }
        return "";
    }

    /**
     * Marks a task as not done.
     *
     * @param i The (index + 1) of the task to unmark.
     * @param isAnnounced Specifies if the action should be announced.
     * @throws TheCountException If the task index is invalid.
     */
    public String unmarkTask(int i, boolean isAnnounced) throws TheCountException {
        if (i < 1 || i > tasks.size()) {
            throw new TheCountException("Invalid task number. I can't count that!");
        }
        Task currTask = this.tasks.get(i - 1);
        currTask.unmark();
        if (isAnnounced) {
            Reply replyToUser = new Reply("MINUS ONE! I've marked this task as not done yet:\n"
                    + "" + currTask.toString());
            return replyToUser.displayMessage();
        }
        return "";
    }

    /**
     * Tags a task with a message.
     *
     * @param i The index of the task to tag.
     * @param message The message to tag the task with.
     * @return A message indicating that the task has been tagged.
     * @throws TheCountException If the task number is invalid.
     */
    public String tagTask(int i, String message) throws TheCountException {
        if (i < 1 || i > tasks.size()) {
            throw new TheCountException("Invalid task number. I can't count that!");
        }
        Task currTask = this.tasks.get(i - 1);
        currTask.tag(message);
        Reply replyToUser = new Reply("I've tagged this task:\n"
                + "" + currTask.toString());
        return replyToUser.displayMessage();
    }


    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task to delete.
     * @throws TheCountException If the task index is invalid.
     */
    public String deleteTask(int i) throws TheCountException {
        if (i < 1 || i > tasks.size()) {
            throw new TheCountException("Invalid task number. I can't delete that!");
        }
        Task currTask = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        Reply replyToUser = new RemoveFromListReply(currTask.toString(), this.tasks.size());
        return replyToUser.displayMessage();
    }

    /**
     * Finds tasks containing the specified keyword (non case-sensitive).
     *
     * @param keyword The keyword to search for.
     */
    public String findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (Task task : this.tasks) {
            if (task.getDesc().toLowerCase().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            Reply replyToUser = new Reply("I can't find any matching tasks.");
            return replyToUser.displayMessage();
        } else {
            Reply replyToUser = new PrintList(foundTasks);
            return replyToUser.displayMessage();
        }
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
