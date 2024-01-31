public class InvalidInputException extends ChatbotException {
    public InvalidInputException() {
        super("Dave does not know what to do.\nDave can only do the following things:" +
        "\n1. Record tasks.\n2. Mark tasks as complete/incomplete.\n3. Delete tasks.\n4. List tasks." +
        "\n\nCommands available:\n" +
        "todo <task>,\ndeadline <task> /by <when>,\nevent <task> /from <when> /to <when>,\nmark <task number>,\ndelete <task number>,\nlist");
    }
}
