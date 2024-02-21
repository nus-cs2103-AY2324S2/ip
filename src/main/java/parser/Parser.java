package parser;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;

import exceptions.HowieException;

import ui.Ui;

public class Parser {
    /**
     * Returns a Command class based on input type.
     * @param input An array of strings split by spaces.
     * @return Command
     */
    public Command parseCommand(String[] input) throws HowieException {
        StringBuilder name;
        StringBuilder current;
        String s1 = input[0];
        boolean isInputLengthTwo = input.length != 2;

        switch (s1) {
        case ListCommand.COMMAND:
            return new ListCommand();
        case ByeCommand.COMMAND:
            return new ByeCommand();
        case MarkCommand.COMMAND:
            return getMarkCommand(input, isInputLengthTwo);
        case UnmarkCommand.COMMAND:
            return getUnmarkCommand(input, isInputLengthTwo);
        case TodoCommand.COMMAND:
            name = new StringBuilder();
            for (int j = 1; j < input.length; j++) {
                name.append(input[j]).append(" ");
            }

            TodoCommand todoCommand = getTodoCommand(name);
            if (todoCommand != null) {
                return todoCommand;
            }
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
            DeadlineCommand deadlineCommand = getDeadlineCommand(name, by);
            if (deadlineCommand != null) {
                return deadlineCommand;
            }
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
            EventCommand eventCommand = getEventCommand(name, from, to);
            if (eventCommand != null) {
                return eventCommand;
            }
        case DeleteCommand.COMMAND:
            return getDeleteCommand(input, isInputLengthTwo);
        case FindCommand.COMMAND:
            return getFindCommand(input, isInputLengthTwo);
        case HelpCommand.COMMAND:
            return new HelpCommand();
        default:
            return new InvalidCommand();
        }
    }

    private static EventCommand getEventCommand(StringBuilder name, StringBuilder from, StringBuilder to) throws HowieException {
        if (name.length() == 0) {
            Ui.emptyTaskMessage();
        } else if (from.length() == 0 || to.length() == 0) {
            Ui.invalidFormat();
        } else {
            return new EventCommand(name.toString(), from.toString(), to.toString());
        }
        return null;
    }

    private static DeadlineCommand getDeadlineCommand(StringBuilder name, StringBuilder by) throws HowieException {
        if (name.length() == 0) {
            Ui.emptyTaskMessage();
        } else if (name.compareTo(by) == 0) {
            Ui.invalidFormat();
        } else {
            return new DeadlineCommand(name.toString(), by.toString());
        }
        return null;
    }

    private static TodoCommand getTodoCommand(StringBuilder name) throws HowieException {
        if (name.length() == 0) {
            Ui.emptyTaskMessage();
        } else {
            return new TodoCommand(name.toString());
        }
        return null;
    }

    private static FindCommand getFindCommand(String[] input, boolean isInputLengthTwo) throws HowieException {
        if (isInputLengthTwo) {
            throw new HowieException("Invalid arguments detected! Please enter a valid keyword! " +
                    "For example: find book");
        }
        return new FindCommand(input[1]);
    }

    private static DeleteCommand getDeleteCommand(String[] input, boolean isInputLengthTwo) throws HowieException {
        if (isInputLengthTwo) {
            throw new HowieException("Invalid arguments detected! Hint: delete [index]");
        }
        return new DeleteCommand(Integer.parseInt(input[1]));
    }

    private static UnmarkCommand getUnmarkCommand(String[] input, boolean isInputLengthTwo) throws HowieException {
        if (isInputLengthTwo) {
            throw new HowieException("Invalid arguments detected! Please enter a index.");
        }
        return new UnmarkCommand(Integer.parseInt(input[1]));
    }

    private static MarkCommand getMarkCommand(String[] input, boolean isInputLengthTwo) throws HowieException {
        if (isInputLengthTwo) {
            throw new HowieException("Invalid arguments detected! Please enter a index.");
        }
        return new MarkCommand(Integer.parseInt(input[1]));
    }
}
