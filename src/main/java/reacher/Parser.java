package reacher;

import reacher.command.*;


public class Parser {
    /**
     * Makes sense of user input and returns the command.
     * @throws ReacherException If input is not a valid command.
     */
    public static Command parse(String input) throws ReacherException {
        String command = getInfo(input, 0);
        switch (command) {
        case ("find"):
            return new FindCommand();
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
    public static String getInfo(String input, int i) throws ReacherException {
        try {
            return input.split(",", 0)[i].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ReacherException("Wrong number of arguments");
        }
    }
}
