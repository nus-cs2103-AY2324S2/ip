package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand(){
    }

    /**
     * Executes the command to exit the program.
     *
     * @param tasks The list of tasks.
     * @param storage The storage object.
     */
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @param command The command to check.
     * @return True if the command is an exit command, false otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
