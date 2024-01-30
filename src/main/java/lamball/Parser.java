package lamball;

public class Parser {
    public static String[] parse(String msg) throws LamballParseException {
        String[] parts = msg.split(" ", 2);
        switch (parts[0]) {
            case "mark": {
                if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
                    throw new LamballParseException("Invalid number, baa.");
                }
                return parts;
            }
            case "unmark": {
                if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
                    throw new LamballParseException("Invalid number, baa.");
                }
                return parts;
            }
            case "bye":
                if (parts.length > 1) {
                    throw new LamballParseException("Do not type anything after 'bye'");
                }
                return parts;
            case "list":
                if (parts.length > 1) {
                    throw new LamballParseException("Do not type anything after 'list'");
                }
                return parts;
            case "todo": {
                // Checks if empty string (nothing after command) or only whitespaces
                if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
                    throw new LamballParseException("Your todo field is empty, baaaaka.");
                }
                return parts;
            }
            case "event": {
                if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
                    // Checks if empty string (nothing after command) or only whitespaces
                    throw new LamballParseException("Your event field is empty, baaaaka.");
                }
                return parts;
            }
            case "deadline": {
                // Checks if empty string (nothing after command) or only whitespaces
                if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
                    throw new LamballParseException("Your deadline field is empty, baaaaka.");
                }
                return parts;
            }
            case "delete": {
                if (!parts[1].matches("-?\\d+")) {
                    throw new LamballParseException("Invalid number, baa.");
                }
                return parts;
            }
            default:
                throw new LamballParseException("Sorry, I don't understaaaaaand your commaaaaand, baa. :(");
        }
    }
}
