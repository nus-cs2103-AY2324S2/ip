package lindi.commands;

import lindi.storage.Storage;
import lindi.task.CreateDeadlineException;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that creates a Deadline upon execution
 */
public class CreateDeadlineCommand extends Command {
    public static final String COMMAND_DEADLINE = "deadline";
    private final String commandString;

    /**
     * Creates command to create a Deadline with the arguments supplied by the user
     *
     * @param commandString user input string
     */
    public CreateDeadlineCommand(String commandString) {
        this.commandString = commandString;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates a Deadline and adds it to task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task createdtask = Task.createDeadline(this.commandString);
            tasks.add(createdtask);
            this.statusMsg = String.format("Okay. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                    createdtask, tasks.size());
            storage.saveToFile(tasks);
        } catch (CreateDeadlineException e) {
            this.statusMsg = e.getMessage();
        }
    }
}
