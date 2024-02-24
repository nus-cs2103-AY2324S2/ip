package jerome.commands;

/**
 * Ends the program execution
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 */
public class ByeCommand extends Command {
    /**
     * Represents the keyword for the "bye" command.
     * The "bye" command ends the program execution.
     */
    public static final String COMMAND_WORD = "bye";

    /** Represents the usage message for the "bye" command. * Usage: bye */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Quits the program.\n"
            + "Example: " + COMMAND_WORD;

    /** Represents the acknowledgment message when the program exits. */
    public static final String MESSAGE_EXIT_ACKNOWLEDGMENT = "\t Exiting as requested ...";

    /**
     * Executes the command and returns the result.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGMENT);
    }

    /**
     * Checks if the given command represents an exit command.
     *
     * @param command the command to check.
     * @return true if the command is an exit command, false otherwise.
     *
     */
    public static boolean isExit(Command command) {
        // instanceof returns false if it is null
        return command instanceof ByeCommand;
    }


}
