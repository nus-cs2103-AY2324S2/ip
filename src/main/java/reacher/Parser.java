package reacher;

import reacher.command.*;

import java.util.Locale;

import static reacher.Parser.Variable.*;


public class Parser {
     public enum Variable {
        COMMAND,
        NAME,
        START,
        END,
        KEYWORD,
        TYPE,
        CHANGE,
         TASK_NO,
         DEADLINE
    }
    /**
     * Makes sense of user input and returns the command.
     * @throws ReacherException If input is not a valid command.
     */
    public static Command getCommand(String input) throws ReacherException {
        String command = getInfo(input, COMMAND).toLowerCase();

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
        case ("help"):
            return new HelpCommand();
        default:
            throw new ReacherException("Not a valid command!");
        }
    }
    public static String getInfo(String input, Variable i) throws ReacherException {
        int c = 0;
        switch (i) {
        case COMMAND:
            c = 0;
            break;
        case END :
            c = 4;
            break;
        case CHANGE:
            c = 2;
            break;
        case NAME:
            c = 1;
            break;
        case START:
            c = 3;
            break;
        case KEYWORD:
            c = 1;
            break;
        case TYPE:
            c = 2;
            break;
        case DEADLINE:
            c = 2;
            break;
        case TASK_NO:
            c = 1;
            break;
        default:
            assert false;
        }
        try {
            return input.split(",", 0)[c].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ReacherException("Wrong number of arguments");
        }
    }
}
