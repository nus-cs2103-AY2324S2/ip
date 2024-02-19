public class Parser {
    static Command parse(String inputString) {
        if (inputString.equals("bye")) {
            return new ExitCommand(inputString);
        }
        if (inputString.equals("list")) {
            return new ShowListCommand(inputString);
        }
        if (inputString.startsWith("mark")) {
            return new MarkCommand(inputString);
        }
        if (inputString.startsWith("unmark")) {
            return new UnMarkCommand(inputString);
        }
        if (inputString.startsWith("todo") || inputString.startsWith("deadline")
                || inputString.startsWith("event")) {
            return new AddCommand(inputString);
        }
        return new DeleteCommand(inputString);

    }
}
