package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command that exits the execution of user interaction
 */
public class ExitCommand extends Command {
    /**
     * Judges if the other command is also exit command.
     *
     * @param other Another command
     * @return True if both are ExitCommands
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof ExitCommand;
    }

    /**
     * Leaves a farewell message.
     *
     * @param storage  Involved in file management
     * @param taskList Active during the execution of the program
     * @return A string that tells the user the exection is complete
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return "Bye! The window will close in 3 seconds. Thank you for for your usage!";
    }

    /**
     * Verifies if it is the command to exit program execution.
     *
     * @return True if it the exit command, and false otherwise;
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
