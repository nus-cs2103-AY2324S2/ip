public class Parser {

    public static Command parse(String fullCommand) throws NoCmdException {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        String [] uCmd = fullCommand.split(" ");
        String firstWord = uCmd[0];
        String furtherDetails = uCmd[1];

        if (firstWord == "mark" || firstWord == "unmark") {
            return new EditCommand(firstWord, furtherDetails);
        } else if (firstWord.equals("todo") || firstWord == "event" || firstWord == "deadline") {
            return new AddCommand(firstWord, furtherDetails);
        } else if (firstWord == "delete") {
            return new DeleteCommand(furtherDetails);
        } else {
            throw new NoCmdException("Please tell me what you want me to do.");
        }

    }
}
