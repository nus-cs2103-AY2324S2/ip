package drew.command;

import drew.storage.Storage;
import drew.storage.TaskList;

/**
 * This class represents the bye command. It contains the logic for executing the command.
 */
public class ByeCommand extends Command {
    /**
     * Static instance of Bye Command.
     */
    public static final Command BYE = new ByeCommand("");

    private ByeCommand(String input) {
        super(input);
    }

    /**
     * Gets the stored instance of a Bye Command.
     * @return A static instance of the Bye Command.
     */
    public static Command getByeCommand() {
        return BYE;
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
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }

    public static boolean isByeCommand(int inputLength, String input) {
        return inputLength == 3 && input.substring(0, 3).equalsIgnoreCase("bye");
    }

}
