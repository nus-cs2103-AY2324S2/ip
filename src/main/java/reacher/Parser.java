package reacher;

import reacher.command.*;

import java.util.Locale;

public class Parser {
    public static Command parse(String input) throws ReacherException {
        input = input.toLowerCase();
        switch (input) {
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
}
