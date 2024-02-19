package hal.command;

import hal.task.TaskList;

/**
 * The UnknownCommand class represents a command for unknown or unrecognized inputs.
 */
public class UnknownCommand extends Command {

    /**
     * Constructs a new UnknownCommand object.
     */
    public UnknownCommand() {
    }

    /**
     * Executes the unknown command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message indicating that the command is unknown.
     */
    @Override
    public String execute(TaskList taskList) {
        return "Unknown command. What are you doing Dave.";
    }
}
