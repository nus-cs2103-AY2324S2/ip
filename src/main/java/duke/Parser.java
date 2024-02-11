package duke;

/**
 * Parser class to parse the user command
 */
public class Parser {

    /**
     * Parse method to break down the user input into relevant pieces
     * @param fullCommand The full user input command
     * @throws DukeException The exception to throw when command is not recognised
     */
    public void parse(String fullCommand) throws DukeException {
        if (!(fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") || fullCommand.startsWith("event")
                || fullCommand.startsWith("list") || fullCommand.startsWith("bye")
                || fullCommand.startsWith("delete"))) {
            Ui ui = new Ui();
            ui.showDivider();
            throw new DukeException("OPPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String[] commandSplit = fullCommand.split(" ");
    }
}
