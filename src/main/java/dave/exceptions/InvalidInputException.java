package dave.exceptions;

public class InvalidInputException extends ChatbotException {

    /**
     * Creates new InvalidInputException.
     * InvalidInputExceptions are those that occur from unrecognisable commands.
     * Prints a message to let the user know the available commands and the parameters for each command.
     */
    public InvalidInputException() {
        super("Dave does not know what to do."
                + "\nPlease help Dave by only entering the available commands."
                + "\n\nCommands available:\n"
                + "- todo <task>,\n- deadline <task> /by <dd/mm/yyyy hhmm>,"
                + "\n- event <task> /from <dd/mm/yyyy hhmm> /to <dd/mm/yyyy hhmm>,"
                + "\n- mark <task number>,\n- delete <task number>,\n- find <keyword>,\n- list,"
                + "\n- remind");
    }
}
