package drew.command;

import java.time.LocalDate;
import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Deadline;
import drew.task.Task;

/**
 * This class represents the Deadline command. It contains the logic for executing the command.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String input) {
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

        int firstBackslashIndex = input.indexOf("/by");
        if (firstBackslashIndex == -1) {
            throw new IllegalArgumentException("Incorrect input. Ensure that date begins with /by");
        }

        String deadlineDescription = input.substring(9, firstBackslashIndex);
        LocalDate deadline = LocalDate.parse(input.substring(firstBackslashIndex + 4).trim());

        Deadline newTask = new Deadline(deadlineDescription, deadline);
        ls.add(newTask);

        reply = "Got it. I've added this task:\n";
        reply = reply + newTask.toStatusString() + "\n";
        listLength++;
        reply = reply + String.format("Now you have %d task(s) in the list.", listLength);

        return reply;
    }

    public static boolean isDeadlineCommand(int inputLength, String input) {
        return inputLength > 8 && input.substring(0, 8).equalsIgnoreCase("deadline");
    }
}
