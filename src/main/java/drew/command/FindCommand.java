package drew.command;

import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Task;

/**
 * This class represents the Find command.
 */
public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        String search = input.substring(4).trim();
        reply = "Here are the matching tasks in your list:i\n";
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            Task task = ls.get(i);
            if (task.toString().contains(search)) {
                matchedTasks.add(task);
            }
        }
        for (int i = 0; i < matchedTasks.size(); i++) {
            reply = reply + Integer.toString(i + 1) + ". "
                    + matchedTasks.get(i).toStatusString() + "\n";
        }
        reply = reply.trim();

        return reply;
    }

    public static boolean isFindCommand(int inputLength, String input) {
        return inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("find");
    }
}
