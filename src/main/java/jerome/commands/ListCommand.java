package jerome.commands;

/**
 * Represents the command to display list of all entries in database.
 */
public class ListCommand extends Command {
    /**
     * Represents the command word for List Event command.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Represents message when there are no events available.
     */
    public static final String MESSAGE_NO_EVENTS = "\t You have not added any tasks!\n"
            + "\t Checkout the todo/event/deadline commands!";

    /**
     * Represents how each individual event should be formatted.
     */
    private static final String MESSAGE_INDIVIDUAL_LISTING_FORMAT = "\t %d. %s";

    /**
     * Executes the command to display a list of all entries in the database.
     *
     * @return a CommandResult containing the formatted list of entries.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(dataStorage.searchResultsInList(""));
    }

}
