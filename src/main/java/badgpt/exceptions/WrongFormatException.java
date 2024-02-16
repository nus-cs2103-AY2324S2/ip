package badgpt.exceptions;

/**
 * Signals that the command was entered using an invalid format.
 */
public class WrongFormatException extends CommandException {
    private String rightUsage = "Please type in the command as follows: ";
    private String example;
    private String brainrot = "";

    /**
     * Creates a new WrongFormatException with the specified message and command. The proper usage of the command along
     * with an example will also be displayed.
     *
     * @param message The error message.
     * @param cmd The command which caused the error.
     */
    public WrongFormatException(String message, String cmd) {
        super(message);

        switch (cmd) {
        case "mark":
            rightUsage += "mark taskNum\n";
            example = "mark 2";
            break;
        case "unmark":
            rightUsage += "unmark taskNum\n";
            example = "unmark 2";
            break;
        case "todo":
            rightUsage += "todo taskDescription\n";
            example = "todo read book\n";
            brainrot = "are you satisfied with that, todo aoi";
            break;
        case "deadline":
            rightUsage += "deadline taskDescription /by YYYY-MM-DD\n";
            example = "deadline return book /by 2024-01-31";
            break;
        case "event":
            rightUsage += "event taskDescription /from YYYY-MM-DD /to YYYY-MM-DD\n";
            example = "event holiday /from 2024-01-31 /to 2024-02-07";
            break;
        case "delete":
            rightUsage += "delete taskNum\n";
            example = "delete 2";
            break;
        case "find":
            rightUsage += "find keyword\n";
            example = "find book";
            break;
        case "date":
            rightUsage += "date YYYY-MM-DD\n";
            example = "date 2024-01-31";
            break;
        }
    }

    /**
     * Returns a string representation of the exception.
     */
    @Override
    public String toString() {
        return rightUsage + "Example usage: " + example + brainrot;
    }
}
