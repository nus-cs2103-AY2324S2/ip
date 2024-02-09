package mona;

/**
 * This class contains all logic for parsing the user input
 */
public class Parser {
    /**
     * Method for determining the type of command given by the user
     * @param input the user input split by the " " delimiter
     * @return Command enum indicating which of the predefined commands was called
     * @throws MonaException thrown when an unknown command is encountered
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
        default:
            throw new MonaException("Mona does not recognise this command!");
        }
    }

    /**
     * Method for getting the rest of the details following the type of task being added to the list
     * @param command Command enum indicating which command was called
     * @param input full user input from the command line
     * @return an array containing the details to be used to instantiate tasks
     */
    public String[] getDetails(Mona.Command command, String input) {
        String[] details = new String[3];
        switch (command) {
        case TODO:
            details[0] = input.substring(5);
            break;
        case DEADLINE:
            String rest = input.substring(9);
            String[] parts = rest.split(" /by ");
            details[0] = parts[0];
            details[1] = parts[1];
            break;
        case EVENT:
            String[] subDetails = input.substring(6).split(" /from ");
            String[] startAndEnd = subDetails[1].split(" /to ");
            details[0] = subDetails[0];
            details[1] = startAndEnd[0];
            details[2] = startAndEnd[1];
            break;
        default:
            break;
        }
        return details;
    }
}
