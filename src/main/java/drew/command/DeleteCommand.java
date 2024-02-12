package drew.command;

import drew.exceptions.InsufficientArgumentsException;
import drew.storage.TaskList;
import drew.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        int taskIndex;
        taskIndex = Integer.parseInt(input.substring(7));
        if (taskIndex > listLength) {
            throw new IllegalArgumentException("This task does not exist!");
        }

        reply = "Ok. I have deleted this task :\n" +
                ls.get(taskIndex - 1).toStatusString() + "\n";
        ls.remove(taskIndex - 1);
        listLength--;

        reply = reply + String.format("Now you have %d task(s) in the list.", listLength);

        return reply;
    }
}
