package Charlie.parser;

import Charlie.commands.*;
import Charlie.exceptions.CharlieException;

public class Parser {

    public static Command parse(String fullCommand) throws CharlieException {

        if (fullCommand.startsWith("delete")) {
            String[] words = fullCommand.split(" ");
            return new DeleteCommand(Integer.valueOf(words[1]));
        } else if (fullCommand.startsWith("todo") || fullCommand.startsWith("event") || fullCommand.startsWith("deadline")) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.startsWith("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("mark")) {
            String[] words = fullCommand.split(" ");
            return new MarkCommand(Integer.valueOf(words[1]));
        }
        else{
            throw new CharlieException("Sorry, unknown command, try again!");
        }
    }


}
