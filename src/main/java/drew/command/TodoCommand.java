package drew.command;

import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Task;
import drew.task.Todo;

/**
 * This class represents the Todo command.
 */
public class TodoCommand extends Command {

    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        String todoDescription = input.substring(5);
        Todo newTask = new Todo(todoDescription);
        ls.add(newTask);

        reply = "Got it. I've added this task:\n";
        reply = reply + newTask.toStatusString() + "\n";
        listLength++;
        reply = reply + String.format("Now you have %d task(s) in the list.", listLength);

        return reply;
    }

    public static boolean isTodoCommand(int inputLength, String input) {
        return inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("todo");
    }
}
