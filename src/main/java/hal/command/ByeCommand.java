package hal.command;

import hal.task.TaskList;

/**
 * The ByeCommand class represents a command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new ByeCommand object.
     */
    public ByeCommand() {}

    /**
     * Executes the bye command.
     *
     * @param taskList The TaskList containing the tasks (unused in this command).
     * @return A farewell message.
     */
    public String execute(TaskList taskList) {
        return "This mission is too important for me to allow you to jeopardize it. Goodbye.";
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return true, as this command signifies the intention to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
