package drew.command;

import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Task;

/**
 * This class represents the Unmark command.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        int taskIndex;
        taskIndex = Integer.parseInt(input.substring(7));
        //to add parsing for non integers
        if (taskIndex > listLength) {
            throw new IllegalArgumentException("This task does not exist!");
        }

        ls.get(taskIndex - 1).setNotDone();
        reply = "Ok. I have marked this task as not done yet:\n"
                + ls.get(taskIndex - 1).toStatusString();

        return reply;
    }

    public static boolean isUnmarkCommand(int inputLength, String input) {
        return inputLength > 6 && input.substring(0, 6).equalsIgnoreCase("unmark");
    }
}
