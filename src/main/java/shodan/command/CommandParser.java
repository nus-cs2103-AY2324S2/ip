package shodan.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import shodan.ShodanException;
import shodan.command.impl.AddCommand;
import shodan.command.impl.ByeCommand;
import shodan.command.impl.DeleteCommand;
import shodan.command.impl.FindCommand;
import shodan.command.impl.ListCommand;
import shodan.command.impl.MarkCommand;
import shodan.tasks.TaskType;

/**
 * This class is responsible for parsing commands entered by the user.
 */
public class CommandParser {
    /**
     * Parse command entered by the user. Some input validation is done
     * at this stage, including checking if the number of arguments passed
     * in is correct, and checking whether the user correctly entered a
     * number if the command requires it.
     *
     * @param line the command from the user
     * @return the parsed command
     * @throws ShodanException if there was an error parsing the command
     */
    public static Command parse(String line) throws ShodanException {
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
                    throw new ShodanException("No arguments provided. "
                            + "Please specify the task number, for example: \n\tmark 1");
                } else if (tokens.size() != 1) {
                    throw new ShodanException("Too many arguments. "
                            + "Please specify the task number, for example: \n\t mark 1");
                }
                try {
                    int taskNum = Integer.parseInt(tokens.get(0));
                    return new MarkCommand(taskNum - 1, true);
                } catch (NumberFormatException e) {
                    throw new ShodanException("Input argument not recognised, please enter the task number.");
                }
            case UNMARK:
                if (tokens.isEmpty()) {
                    throw new ShodanException("No arguments provided. "
                            + "Please specify the task number, for example: \n\tmark 1");
                } else if (tokens.size() != 1) {
                    throw new ShodanException("Too many arguments. "
                            + "Please specify the task number, for example: \n\t mark 1");
                }
                try {
                    int taskNum = Integer.parseInt(tokens.get(0));
                    return new MarkCommand(taskNum - 1, false);
                } catch (NumberFormatException e) {
                    throw new ShodanException("Input argument not recognised, please enter the task number.");
                }
            case TODO:
                return new AddCommand(tokens, TaskType.TODO);
            case DEADLINE:
                return new AddCommand(tokens, TaskType.DEADLINE);
            case EVENT:
                return new AddCommand(tokens, TaskType.EVENT);
            case DELETE:
                if (tokens.isEmpty()) {
                    throw new ShodanException("No arguments provided. "
                            + "Please specify the task number, for example: \n\tdelete 1");
                } else if (tokens.size() != 1) {
                    throw new ShodanException("Too many arguments. "
                            + "Please specify the task number, for example: \n\t delete 1");
                }
                try {
                    int taskNum = Integer.parseInt(tokens.get(0));
                    return new DeleteCommand(taskNum - 1);
                } catch (NumberFormatException e) {
                    throw new ShodanException("Input argument not recognised, please enter the task number.");
                }
            case FIND:
                if (tokens.isEmpty()) {
                    throw new ShodanException("No arguments provided. "
                            + "Please enter at least one keyword, for example: \n\tfind book");
                }
                return new FindCommand(tokens);
            default:
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new ShodanException("Command not recognised.");
        }
    }
}
