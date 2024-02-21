package drew.command;

import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Task;

/**
 * This class represents the Mark command.
 */
public class MarkCommand extends Command {

    public MarkCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        int taskIndex;
        taskIndex = Integer.parseInt(input.substring(5));
        if (taskIndex > listLength) {
            throw new IllegalArgumentException("This task does not exist!");
        }

        ls.get(taskIndex - 1).setDone();
        reply = "Well done! I have marked this task as done:\n"
                + ls.get(taskIndex - 1).toStatusString();

        return reply;
    }

    public static boolean isMarkCommand(int inputLength, String input) {
        return inputLength > 4 && input.substring(0, 4).equalsIgnoreCase("mark");
    }
}
