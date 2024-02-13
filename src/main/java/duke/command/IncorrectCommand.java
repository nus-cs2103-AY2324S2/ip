package duke;

/**
 * Represents a command that is not recognised by the program.
 */
public class IncorrectCommand extends Command {

    public IncorrectCommand() {
    }

    /**
     * Executes the command and prints an error message.
     *
     * @param tasks The list of tasks.
     * @param storage The storage object.
     */
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Invalid command");
    }
}
