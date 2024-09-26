package parsing;

/**
 * A stub class for parsing user input into command arguments.
 * This class is used for testing purposes.
 */
public class ParserStub {

    /**
     * Parses the input string into command arguments and returns the result.
     *
     * @param input the input string
     * @return the parsed command or an error message
     */
    public String parseStub(String input) {
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        switch (command) {
        case "bye":
        case "list":
        case "getcommands":
            return input;
        case "mark":
        case "unmark":
        case "delete":
            return parseMarkUnmarkDeleteStub(splitInput, command);
        case "todo":
            return parseTodoStub(input, command);
        case "deadline":
            return parseDeadlineStub(input, command);
        case "event":
            return parseEventStub(input, command);
        case "find":
            return parseFindStub(input, command);
        default:
            return parseDefaultStub();
        }
    }

    private String parseMarkUnmarkDeleteStub(String[] splitInput, String command) {
        try {
            int index = Integer.parseInt(splitInput[1]);
            return command + " " + splitInput[1];
        } catch (IndexOutOfBoundsException e) {
            return "Brother, key in " + command + " <space> then a valid number";
        } catch (NumberFormatException n) {
            return "You tell me now what task am I supposed to " + command
                    + " if you don't provide me with a number?";
        }
    }

    private String parseTodoStub(String input, String command) {
        try {
            String[] info = input.split("todo ");
            if (info[1].isBlank()) {
                return "Help la, can just tell me what is the name of your task anot?";
            }
            return command + " " + info[1].trim();
        } catch (IndexOutOfBoundsException e) {
            return "You trying to test my patience ah? Type \"get commands\" if u blur"
                    + " and dunno how to use me properly.";
        }
    }

    private String parseDeadlineStub(String input, String command) {
        try {
            String[] info = input.split("/");
            if (info[0].split("deadline ")[1].isBlank() || info[1].isBlank()) {
                return "Help la, can just tell me what is the name of your task anot?";
            }
            return command + " " + info[0].substring(9).trim() + " " + info[1].trim();
        } catch (IndexOutOfBoundsException e) {
            return "You trying to test my patience ah? Check that u got key in the deadline lehhh\n"
                    + "Type \"get commands\" if u blur and dunno how to use me properly.";
        }
    }

    private String parseEventStub(String input, String command) {
        try {
            String[] info = input.split("/");
            if (info[0].split("event ")[1].isBlank() || info[1].isBlank() || info[2].isBlank()) {
                return "Help la, can just tell me what is the name of your task anot?";
            }
            return command + " " + info[0].substring(6).trim() + " "
                    + info[1].trim() + " " + info[2].trim();
        } catch (IndexOutOfBoundsException e) {
            return "Eh brother last warning ah. Check that u got key in the start and end time\n"
                    + "Type \"get commands\" if u blur and dunno how to use me properly.";
        }
    }

    private String parseFindStub(String input, String command) {
        try {
            String[] info = input.split("find ");
            return command + " " + info[1];
        } catch (IndexOutOfBoundsException e) {
            return "What u want me to find??";
        }
    }

    private String parseDefaultStub() {
        return "Sorry bro, idk what that means. You try type in \"getcommands\" then see if got what u want.";
    }
}
