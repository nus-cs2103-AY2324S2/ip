package drew.command;

import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Task;

/**
 * This class represents the Delete command.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }
    /**
     * Executes the command
     * @param tasks Tasklist object that contains the tasks.
     * @param storage Storage object that handles storage related tasks.
     * @return The response from the task.
     * @throws IllegalArgumentException Thrown when the command is called with wrong arguments.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        int taskIndex;
        taskIndex = Integer.parseInt(input.substring(7));
        if (taskIndex > listLength) {
            throw new IllegalArgumentException("This task does not exist!");
        }

        reply = "Ok. I have deleted this task :\n"
                + ls.get(taskIndex - 1).toStatusString() + "\n";
        ls.remove(taskIndex - 1);
        listLength--;

        reply = reply + String.format("Now you have %d task(s) in the list.", listLength);

        return reply;
    }

    public static boolean isDeleteCommand(int inputLength, String input) {
        return inputLength >= 6 && input.substring(0, 6).equalsIgnoreCase("delete");
    }
}
