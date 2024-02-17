package reacher;

import reacher.command.*;


public class Parser {
    /**
     * Makes sense of user input and returns the command.
     * @throws ReacherException If input is not a valid command.
     */
    public static Command parse(String input) throws ReacherException {
        input = input.toLowerCase();
        switch (input) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        case ("edit"):
            return new EditCommand();
        case ("add"):
            return new AddCommand();
        default:
            throw new ReacherException("Not a valid command!");
        }
    }
}
