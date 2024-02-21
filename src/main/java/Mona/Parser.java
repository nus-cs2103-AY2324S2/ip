package mona;

/**
 * The Parser class is responsible for parsing user input and extracting relevant details for processing.
 */
public class Parser {

    /**
     * Parses the current command based on the first element of the input array.
     *
     * @param input The user input split into an array.
     * @return The corresponding Command enum indicating the type of command.
     * @throws MonaException If the command is not recognized by Mona.
     */
    public Mona.Command getCurrentCommand(String[] input) throws MonaException {
        switch (input[0]) {
        case "bye":
            return Mona.Command.BYE;
        case "list":
            return Mona.Command.LIST;
        case "deadline":
            return Mona.Command.DEADLINE;
        case "todo":
            return Mona.Command.TODO;
        case "event":
            return Mona.Command.EVENT;
        case "mark":
            return Mona.Command.MARK;
        case "unmark":
            return Mona.Command.UNMARK;
        case "delete":
            return Mona.Command.DELETE;
        case "find":
            return Mona.Command.FIND;
        case "update":
            return Mona.Command.UPDATE;
        default:
            throw new MonaException("Mona does not recognise this command!");
        }
    }

    /**
     * Extracts details from the user input based on the command type.
     *
     * @param command The Command enum representing the type of command.
     * @param input   The user input string.
     * @return An array of strings containing the extracted details.
     * @throws MonaException If there are insufficient details provided for the command.
     */
    public String[] getDetails(Mona.Command command, String input) throws MonaException {
        String[] details = new String[3];
        switch (command) {
        case TODO:
            getTodoDetails(details, input);
            break;
        case DEADLINE:
            getDeadlineDetails(details, input);
            break;
        case EVENT:
            getEventDetails(details, input);
            break;
        default:
            break;
        }
        return details;
    }

    /**
     * Extracts details for a todo command.
     *
     * @param details The array to store the extracted details.
     * @param input   The user input string.
     * @throws MonaException If there are insufficient details provided for the todo command.
     */
    private void getTodoDetails(String[] details, String input) throws MonaException {
        if (input.length() <= 5) {
            throw new MonaException("NOOOOO! I need details to create a todo :(");
        }
        details[0] = input.substring(5);
    }

    /**
     * Extracts details for a deadline command.
     *
     * @param details The array to store the extracted details.
     * @param input   The user input string.
     * @throws MonaException If there are insufficient details provided for the deadline command.
     */
    private void getDeadlineDetails(String[] details, String input) throws MonaException {
        if (input.length() <= 9) {
            throw new MonaException("NOOOOO! I need details to create a deadline :(");
        }
        String rest = input.substring(9);
        String[] parts = rest.split(" /by ");
        details[0] = parts[0];
        details[1] = parts[1];
    }

    /**
     * Extracts details for an event command.
     *
     * @param details The array to store the extracted details.
     * @param input   The user input string.
     * @throws MonaException If there are insufficient details provided for the event command.
     */
    private void getEventDetails(String[] details, String input) throws MonaException {
        if (input.length() <= 6) {
            throw new MonaException("NOOOOO! I need details to create an event :(");
        }
        String[] subDetails = input.substring(6).split(" /from ");
        String[] startAndEnd = subDetails[1].split(" /to ");
        details[0] = subDetails[0];
        details[1] = startAndEnd[0];
        details[2] = startAndEnd[1];
    }
}
