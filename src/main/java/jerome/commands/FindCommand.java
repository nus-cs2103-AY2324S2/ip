package jerome.commands;

/**
 * Represents the command to add an event to the data storage.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class FindCommand extends Command {

    /**
     * Represents the command word for Find command.
     */
    public static final String COMMAND_WORD = "find";

    /**
     * Represents a message displayed when the search term is empty.
     */
    public static final String MESSAGE_EMPTY_SEARCH_TERM =
            "\t If you would like to list everything, please use the list command.";

    /**
     * Represents how each individual event should be formatted.
     */
    private static final String MESSAGE_INDIVIDUAL_LISTING_FORMAT = "\t %d. %s";

    /**
     * The target index stores the search term provided by the user.
     */
    private String searchString;

    
    public FindCommand(String searchTerm) {
        this.searchString = searchTerm;
    }

    /**
     * Executes the command to display a list of all entries in the database.
     *
     * @return a CommandResult containing the formatted list of entries that contains required keywords.
     */
    public CommandResult execute() {
        return new CommandResult(dataStorage.searchResultsInList(searchString));
    }
}
