package exceptions;
public class InvalidInputException extends ChatbotException {
    public InvalidInputException() {
        super("Dave does not know what to do." +
        "\nPlease help Dave by only entering the available commands." +
        "\n\nCommands available:\n" +
        "todo <task>,\ndeadline <task> /by <dd-mm-yyyy hhmm>,\nevent <task> /from <dd-mm-yyyy hhmm> /to <dd-mm-yyyy hhmm>," +
        "\nmark <task number>,\ndelete <task number>,\nlist");
    }
}
