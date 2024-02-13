package thecount.ui;

import java.util.ArrayList;

import thecount.task.Task;

/**
 * Represents a reply message displaying a list of tasks.
 */
public class PrintList extends Reply {
    private ArrayList<Task> tasks;

    /**
     * Constructs a PrintList object with the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public PrintList(ArrayList<Task> tasks) {
        super("Here are the tasks in your list:");
        this.tasks = tasks;
    }

    /**
     * Displays the list of tasks.
     */
    @Override
    public String displayMessage() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            String taskLine = (i + 1) + "." + tasks.get(i).toString();
            message = message.concat("\n" + taskLine);
        }
        Reply replyToUser = new Reply(message);
        return replyToUser.displayMessage();
    }

    /**
     * Retrieves the list of tasks to be written.
     * To write to file, the list of tasks is formatted as a string.
     *
     * @return The formatted list of tasks.
     */
    public String getListToWrite() {
        String message = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            String taskLine = (i + 1) + " | " + currTask.getType() + " | "
                    + (currTask.isDone() ? 1 : 0) + " | " + currTask.getDesc();
            message = message.concat(taskLine + "\n");
        }
        return message;
    }
}
