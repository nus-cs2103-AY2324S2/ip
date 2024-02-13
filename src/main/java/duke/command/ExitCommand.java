package duke;

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
    public void execute(TaskList tasks, Storage storage) {
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
