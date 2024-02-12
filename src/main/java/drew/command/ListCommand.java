package drew.command;

import drew.storage.TaskList;
import drew.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }
    @Override
    public String execute(TaskList tasks) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        reply = reply + "Tasks in your list, you have." + "\n";
        for (int i = 0; i < listLength; i++) {
            reply = reply + Integer.toString(i + 1) + ". " +
                    ls.get(i).toStatusString() + "\n";
        }
        reply = reply + String.format("%d task(s) in the list, there are.", listLength);

        return reply;
    }
}
