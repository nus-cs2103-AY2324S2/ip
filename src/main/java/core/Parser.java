package core;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ShowTimeCommand;
import commands.TagCommand;
import commands.UnknownCommand;
import commands.UnmarkCommand;
import enums.Commands;

/**
 * The Parser class is responsible for parsing user input and returning the corresponding command.
 */
public class Parser {
    /**
     * Parses the user input and returns the appropriate command object.
     *
     * @param inputs The user input to be parsed.
     * @return A command object based on the parsed input.
     */
    public static Command parse(String... inputs) {
        for (String input : inputs) {
            String[] parts = input.split(" ", 2);

            try {
                Commands commandEnum = Commands.valueOf(parts[0].toUpperCase());

                switch (commandEnum) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case FIND:
                    return new FindCommand(input);
                case MARK:
                    return new MarkCommand(input);
                case UNMARK:
                    return new UnmarkCommand(input);
                case DELETE:
                    return new DeleteCommand(input);
                case TODO:
                    // Fallthrough
                case DEADLINE:
                    // Fallthrough
                case EVENT:
                    return new AddCommand(input);
                case DATE:
                    // Fallthrough
                case TIME:
                    return new ShowTimeCommand();
                case TAG:
                    return new TagCommand(input);
                default:
                    return new UnknownCommand();
                }
            } catch (IllegalArgumentException e) {
                return new UnknownCommand();
            }
        }
        return null;
    }
}
