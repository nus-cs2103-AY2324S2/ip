package luke.exception;

/**
 * Thrown when the first word in the user input is valid, but the input is not in the correct format
 * (based on the first word).
 */
public class InvalidSyntaxException extends Throwable {
    private String command;
    private String syntax;

    /**
     * Gives a certain syntax based on the command given.
     * @param command The first word in the user input.
     */
    public InvalidSyntaxException(String command) {
        assert command.equals("bye") || command.equals("list") || command.equals("mark") || command.equals("unmark")
                || command.equals("todo") || command.equals("deadline") || command.equals("event")
                || command.equals("delete") || command.equals("find");
        this.command = command;
        switch (command) {
        case "bye":
            syntax = "bye (just 'bye', really)";
            break;
        case "list":
            syntax = "list (just 'list', really)";
            break;
        case "mark":
            syntax = "mark [task number]";
            break;
        case "unmark":
            syntax = "unmark [task number]";
            break;
        case "todo":
            syntax = "todo [task description]";
            break;
        case "deadline":
            syntax = "deadline [task description] /by [deadline]";
            break;
        case "event":
            syntax = "event [task description] /from [start date] /to [end date]";
            break;
        case "delete":
            syntax = "delete [task number]";
            break;
        case "find":
            syntax = "find [keyword(s)]";
            break;
        default: // unreachable
        }
    }

    @Override
    public String toString() {
        return "\nSorry, the format for the command is invalid. The syntax for "
                + command + " is: \n" + syntax;
    }
}
