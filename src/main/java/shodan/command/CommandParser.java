package shodan.command;

import shodan.ShodanException;
import shodan.command.impl.AddCommand;
import shodan.command.impl.ByeCommand;
import shodan.command.impl.DeleteCommand;
import shodan.command.impl.ListCommand;
import shodan.command.impl.MarkCommand;
import shodan.tasks.Task;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CommandParser {
    public static Command parse(String line) throws IllegalArgumentException, ShodanException {
        line = line.stripLeading();
        if (line.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> tokens = new LinkedList<String>(Arrays.asList(line.split(" ")));
        String command = tokens.remove(0);
        try {
            switch (CommandType.valueOf(command.toUpperCase())) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                if (tokens.isEmpty()) {
                    throw new ShodanException("No arguments provided. Please specify the task number, for example: \n\tmark 1");
                } else if (tokens.size() != 1) {
                    throw new ShodanException("Too many arguments. Please specify the task number, for example: \n\t mark 1");
                }
                try {
                    int taskNum = Integer.parseInt(tokens.get(0));
                    return new MarkCommand(taskNum-1, true);
                } catch (NumberFormatException e) {
                    throw new ShodanException("Input argument not recognised, please enter the task number.");
                }
            case UNMARK:
                if (tokens.isEmpty()) {
                    throw new ShodanException("No arguments provided. Please specify the task number, for example: \n\tmark 1");
                } else if (tokens.size() != 1) {
                    throw new ShodanException("Too many arguments. Please specify the task number, for example: \n\t mark 1");
                }
                try {
                    int taskNum = Integer.parseInt(tokens.get(0));
                    return new MarkCommand(taskNum-1, false);
                } catch (NumberFormatException e) {
                    throw new ShodanException("Input argument not recognised, please enter the task number.");
                }
            case TODO:
            case DEADLINE:
            case EVENT:
                return new AddCommand(tokens, command);
            case DELETE:
                if (tokens.isEmpty()) {
                    throw new ShodanException("No arguments provided. Please specify the task number, for example: \n\tdelete 1");
                } else if (tokens.size() != 1) {
                    throw new ShodanException("Too many arguments. Please specify the task number, for example: \n\t delete 1");
                }
                try {
                    int taskNum = Integer.parseInt(tokens.get(0));
                    return new DeleteCommand(taskNum-1);
                } catch (NumberFormatException e) {
                    throw new ShodanException("Input argument not recognised, please enter the task number.");
                }
            default:
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new ShodanException("Command not recognised.");
        }
    }
}
