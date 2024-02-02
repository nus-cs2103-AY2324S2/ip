package lamball;

/**
 *  Contains methods related to parsing user's inputs.
 *
 * @author ongzhili
 */
public class Parser {

    /**
     * Parses command into String array in the form command type, arguments.
     *
     * @param msg Original input command.
     * @return String array of length 2, containing command type and arguments respectively.
     * @throws LamballParseException if invalid arguments are parsed.
     */
    public static String[] parse(String msg) throws LamballParseException {
        String[] parts = msg.split(" ", 2);
        switch (parts[0]) {
            case "delete":
            case "mark":
            case "unmark": {
                if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
                    throw new LamballParseException("Invalid number, baa.");
                }
                return parts;
            }
            case "bye":
            case "list":
                if (parts.length > 1) {
                    throw new LamballParseException("Do not type anything after \"" + parts[0] + "\"" );
                }
                return parts;
            case "todo":
            case "deadline":
            case "event": {
                if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
                    // Checks if empty string (nothing after command) or only whitespaces
                    throw new LamballParseException("Your " + parts[0] + " field is empty, baaaaka.");
                }
                return parts;
            }
            default:
                throw new LamballParseException("Sorry, I don't understaaaaaand your commaaaaand, baa. :(");
        }
    }
}
