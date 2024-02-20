package notduke.parser;

import notduke.command.*;
import notduke.notdukeexception.NotDukeException;
import notduke.notdukeexception.NotDukeInvalidCommand;
import notduke.notdukeexception.NotDukeMissingArgument;

public class Parser {
    /**
     * Returns the corresponding command given the input string.
     * @param fullCommand the String command to be parsed
     * @return the command corresponding to the string
     * @throws NotDukeInvalidCommand if string is not a valid command
     */
    public static Command parse(String fullCommand) throws NotDukeException {
        String[] input = fullCommand.split(" ", 2);
        String command = input[0];

        try {
            if (command.equals("bye")) {
                return new ByeCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.equals("unmark")) {
                return new UnmarkCommand(input[1]);
            } else if (command.equals("mark")) {
                return new MarkCommand(input[1]);
            } else if (command.equals("todo")) {
                return new TodoCommand(input[1]);
            } else if (command.equals("deadline")) {
                return new DeadlineCommand(input[1]);
            } else if (command.equals("event")) {
                return new EventCommand(input[1]);
            } else if (command.equals("delete")) {
                return new DeleteCommand(input[1]);
            } else if (command.equals("check")) {
                return new CheckCommand(input[1]);
            } else if (command.equals("find")) {
                return new FindCommand(input[1]);
            } else if (command.equals("remind")) {
                return new RemindCommand();
            } else {
                throw new NotDukeInvalidCommand(fullCommand);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NotDukeMissingArgument(command);
        }
    }
}
