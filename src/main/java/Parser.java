public class Parser {

    public static Command parse(String fullCommand) throws NoCmdException {
        String [] uCmd = fullCommand.split(" ", 2);
        if (uCmd.length < 2) {
            if (fullCommand.equals("list")) {
                return new ListCommand();
            } else if (fullCommand.equals("bye")) {
                return new ExitCommand();
            } else {
                throw new NoCmdException("Please tell me what you want me to do.");
            }
        } else {
            String firstWord = uCmd[0].trim();
            String furtherDetails = uCmd[1];
            System.out.println(furtherDetails);
            if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                return new EditCommand(firstWord, furtherDetails);
            } else if (firstWord.equals("todo") || firstWord.equals("event") || firstWord.equals("deadline")) {
                return new AddCommand(firstWord, furtherDetails);
            } else if (firstWord.equals("delete")) {
                return new DeleteCommand(furtherDetails);
            } else {
                throw new NoCmdException("Please tell me what you want me to do.");
            }
        }


    }
}
