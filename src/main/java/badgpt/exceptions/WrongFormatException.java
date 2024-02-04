package badgpt.exceptions;

public class WrongFormatException extends CommandException {
    private String rightUsage = "Please type in the command as follows: ";
    private String example;
    private String brainrot;

    public WrongFormatException(String message, String cmd) {
        super(message);

        switch (cmd) {
        case "mark":
            rightUsage += "mark taskNum\n";
            example = "mark 2";
            brainrot = "";
            break;
        case "unmark":
            rightUsage += "unmark taskNum\n";
            example = "unmark 2";
            brainrot = "";
            break;
        case "todo":
            rightUsage += "todo taskDescription\n";
            example = "todo read book\n";
            brainrot = "are you satisfied with that, todo aoi";
            break;
        case "deadline":
            rightUsage += "deadline taskDescription /by YYYY-MM-DD\n";
            example = "deadline return book /by 2024-01-31";
            brainrot = "";
            break;
        case "event":
            rightUsage += "event taskDescription /from YYYY-MM-DD /to YYYY-MM-DD\n";
            example = "event holiday /from 2024-01-31 /to 2024-02-07";
            brainrot = "";
            break;
        case "delete":
            rightUsage += "delete taskNum\n";
            example = "delete 2";
            break;
        }
    }

    @Override
    public String toString() {
        return rightUsage + "Example: " + example + brainrot;
    }
}
