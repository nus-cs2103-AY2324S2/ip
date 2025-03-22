package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.command.Command;

/**
 * Represents a command that is not recognised by the program.
 */
public class IncorrectCommand extends Command {

    /**
     * Executes the command and prints an error message.
     *
     * @param tasks The list of tasks.
     * @param storage The storage object.
     */
    public String execute(TaskList tasks, Storage storage) {
       return "Invalid command";
    }
}
