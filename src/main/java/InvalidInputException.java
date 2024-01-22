public class InvalidInputException extends ChatbotException {
    public InvalidInputException() {
        super("Dave does not know what to do.\nDave can only do the following things:\n1. Record tasks.\n\nCommands available:\n" +
        "todo <task>,\ndeadline <task> /by <when>,\nevent <task> /from <when> /to <when>");
    }
}
