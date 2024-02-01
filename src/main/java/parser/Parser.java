package parser;

import commands.*;
import exceptions.DukeException;

public class Parser {
    /**
     * Returns a Command class based on input type.
     * @param input An array of strings split by spaces.
     * @return Command
     */
    public Command parseCommand(String[] input) throws DukeException {
        StringBuilder name;
        StringBuilder current;
        String s1 = input[0];
        switch (s1) {

        case ListCommand.COMMAND:
            return new ListCommand();

        case ByeCommand.COMMAND:
            return new ByeCommand();

        case MarkCommand.COMMAND:
            if (input.length != 2) {
                throw new DukeException("Invalid arguments detected! Please enter a index.");
            }
            return new MarkCommand(Integer.parseInt(input[1]));

        case UnmarkCommand.COMMAND:
            if (input.length != 2) {
                throw new DukeException("Invalid arguments detected! Please enter a index.");
            }
            return new UnmarkCommand(Integer.parseInt(input[1]));

        case TodoCommand.COMMAND:
            name = new StringBuilder();
            for (int j = 1; j < input.length; j++) {
                name.append(input[j]).append(" ");
            }
            return new TodoCommand(name.toString());

        case DeadlineCommand.COMMAND:
            name =  new StringBuilder();
            StringBuilder by =  new StringBuilder();
            current = name;
            for (int i = 1; i < input.length; i++) {
                if (input[i].equals("/by")) {
                    name = current;
                    current = by;
                    continue;
                }
                current.append(input[i]).append(" ");
            }
            by = current;
            return new DeadlineCommand(name.toString(), by.toString());

        case EventCommand.COMMAND:
            name =  new StringBuilder();
            StringBuilder from =  new StringBuilder();
            StringBuilder to =  new StringBuilder();
            current = name;
            for (int i = 1; i < input.length; i++) {
                if (input[i].equals("/from")) {
                    name = current;
                    current = from;
                    continue;
                } else if (input[i].equals("/to")) {
                    from = current;
                    current = to;
                    continue;
                }
                current.append(input[i]).append(" ");
            }
            return new EventCommand(name.toString(), from.toString(), to.toString());

        case DeleteCommand.COMMAND:
            return new DeleteCommand(Integer.parseInt(input[1]));

        case FindCommand.COMMAND:
            if (input.length != 2) {
                throw new DukeException("Invalid arguments detected! Please enter a valid keyword! " +
                        "For example: find book");
            }
            return new FindCommand(input[1]);
        default:
            return new HelpCommand();
        }

    }
}
