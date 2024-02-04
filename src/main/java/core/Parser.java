package core;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.ListOnCommand;
import commands.MarkCommand;
import commands.ShowTimeCommand;
import commands.UnknownCommand;
import commands.UnmarkCommand;

import enums.Commands;

public class Parser {
    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);

        try {
            Commands commandEnum = Commands.valueOf(parts[0].toUpperCase());

            switch(commandEnum) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case LIST_ON:
                return new ListOnCommand(input);
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
            default:
                return new UnknownCommand();
            }
        } catch (IllegalArgumentException e) {
            return new UnknownCommand();
        }
    }
}
