package lex.parser.command;

import lex.ui.Ui;

/**
 * Represents a command to show the list of commands.
 */
public class HelpCommand implements Command {
    /**
     * The message to show the list of commands.
     */
    public static final String MESSAGE = "Here are the commands you can use:\n"
            + "1. todo <description> - Adds a todo task.\n"
            + "2. deadline <description> /by <date> - Adds a deadline task.\n"
            + "3. event <description> /from <date> /to <date> - Adds an event task.\n"
            + "4. list - Lists all tasks.\n"
            + "5. mark <index> - Marks a task as done.\n"
            + "6. unmark <index> - Unmarks a task as done.\n"
            + "7. delete <index> - Deletes a task.\n"
            + "8. find <keyword> - Finds tasks with the given keyword.\n"
            + "9. help - Shows the list of commands.\n"
            + "10. bye - Exits the program.";
    private final Ui ui;

    /**
     * Constructor for the HelpCommand class.
     *
     * @param ui The user interface.
     */
    public HelpCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public boolean execute() throws Exception {
        ui.print(MESSAGE);

        return false;
    }
}
