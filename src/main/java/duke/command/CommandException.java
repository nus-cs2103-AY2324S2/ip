package duke.command;

/**
 * Class for all Command based errors encountered.
 *
 * @author KohGuanZeh
 */
public class CommandException extends Exception {
    public CommandException(String errorMessage) {
        super(errorMessage);
    }
}
