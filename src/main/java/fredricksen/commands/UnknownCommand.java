package fredricksen.commands;

/**
 * Represents an "Unknown" Command, which extends the Command class.
 * An "Unknown" Command is a command that creates an UnknownCommand object.
 *
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {}

    /**
     * Executes the Unknown command.
     * Formats the String that tells the user it does not recognise specified input.
     *
     * @return A String that is formatted that tells the user it does not recognise
     *      the input specified by the user.
     */
    @Override
    public String execute() {
        return "Sorry Old Man Fredricksen don't recognise this input! "
                + "Type \"help\" if you need a guide on input format!";
    }
}

