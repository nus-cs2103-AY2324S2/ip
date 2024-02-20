package drew.command;

import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Task;

/**
 * This class represents hte List command.
 */
public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        reply = reply + "Tasks in your list, you have." + "\n";
        reply += tasks.getTaskList();
        reply = reply + String.format("%d task(s) in the list, there are.", listLength);

        return reply;
    }

    public static boolean isListCommand(int inputLength, String input) {
        return inputLength == 4 && input.substring(0, 4).equalsIgnoreCase("list");
    }

}
