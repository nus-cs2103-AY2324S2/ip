package drew.command;

import drew.exceptions.InsufficientArgumentsException;
import drew.storage.TaskList;
import drew.task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command{

    public MarkCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        int taskIndex;
        taskIndex = Integer.parseInt(input.substring(5));
        if (taskIndex > listLength) {
            throw new IllegalArgumentException("This task does not exist!");
        }

        ls.get(taskIndex - 1).setDone();
        reply = "Well done! I have marked this task as done:\n" +
                ls.get(taskIndex - 1).toStatusString();

        return reply;
    }
}
