package lamball;

public class Parser {
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
            case "find": {
                if (parts.length < 2 || parts[1] == null) {
                    throw new LamballParseException("Invalid query");
                }
                return parts;
            }
            default:
                throw new LamballParseException("Sorry, I don't understaaaaaand your commaaaaand, baa. :(");
        }
    }
}
