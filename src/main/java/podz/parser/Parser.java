package podz.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import podz.commands.ByeCommand;
import podz.commands.Command;
import podz.commands.DeadlineCommand;
import podz.commands.DeleteCommand;
import podz.commands.EventCommand;
import podz.commands.FindCommand;
import podz.commands.IncorrectCommand;
import podz.commands.ListCommand;
import podz.commands.MarkCommand;
import podz.commands.TodoCommand;
import podz.commands.UnmarkCommand;
import podz.exceptions.PodzException;
import podz.task.Deadline;
import podz.task.Event;
import podz.task.Todo;

/**
 * Represents the parser of the user's input.
 */
public class Parser {
    /**
     * Represents the available commands for the parser.
     */
    public enum Commands {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        BYE,
        UNKNOWN;

        private static final ArrayList<String> COMMAND_STRINGS = Arrays.stream(Commands.values())
                .map(Enum::name)
                .collect(Collectors.toCollection(ArrayList::new));

        /**
         * Returns the enum value corresponding to the command input or a default value.
         * 
         * @param command the string to be converted into an enum value.
         * @return the enum value representing the string.
         */
        public static Commands valueOfOrElse(String command) {
            try {
                return Commands.valueOf(mapToEnumString(command));
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }

        private static String mapToEnumString(String command) {
            for (String s : COMMAND_STRINGS) {
                if (s.startsWith(command)) {
                    return s;
                }
            }
            return command;
        }
    }

    /**
     * Returns the <code>Command</code> based on the user's input.
     * 
     * @param userInput the input of the user
     * @return the <code>Command</code> based on specified input
     */
    public Command parseCommand(String userInput) {
        String[] inputs = userInput.split(" ", 2);
        String command = inputs[0];
        Commands cmd = Commands.valueOfOrElse(command.toUpperCase());

        switch (cmd) {
        case LIST:
            return new ListCommand();

        case MARK:
            return prepareMark(inputs);

        case UNMARK:
            return prepareUnmark(inputs);
            
        case TODO:
            return prepareTodo(inputs);
            
        case DEADLINE:
            return prepareDeadline(inputs);

        case EVENT:
            return prepareEvent(inputs);

        case DELETE:
            return prepareDelete(inputs);

        case BYE:
            return new ByeCommand();

        case FIND:
            return prepareFind(inputs);

        case UNKNOWN:
            return prepareUnknown();
                    
        default:
            throw new AssertionError(cmd);
        }
    }

    private Command prepareMark(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new PodzException(
                    "Oops! It looks like there was an issue with the mark command format.");
            }

            int taskIndex = parseTaskIndex(inputs[1]);
            return new MarkCommand(taskIndex);
        } catch (PodzException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareUnmark(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new PodzException("Wrong unmark command format!!");
            }

            int taskIndex = parseTaskIndex(inputs[1]);
            return new UnmarkCommand(taskIndex);
        } catch (PodzException e) {
            return new IncorrectCommand(e);
        }
    }

    private int parseTaskIndex(String input) throws PodzException {
        try {
            return Integer.parseInt(input.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new PodzException("Please provide a valid index and try again.");
        }
    }

    private Command prepareTodo(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new PodzException("Oh no! Your todo command is "
                                        + "incomplete!!");
            }

            Todo todo = new Todo(inputs[1]);
            return new TodoCommand(todo);
        } catch (PodzException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareDeadline(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new PodzException("OOPS!!! The deadline task "
                                        + "cannot be empty.");
            }
            // separate to description: deadlineInfo[0]: return book 
            // and deadline: deadlineInfo[1]: by Sunday
            String[] deadlineInfo = inputs[1].split("/by", 2);

            if (deadlineInfo.length != 2) {
                throw new PodzException("OOPS!!! Please add "
                                        + "a valid deadline.");
            }

            Deadline deadline = new Deadline(deadlineInfo[0].strip(), 
                                            deadlineInfo[1].strip());

            return new DeadlineCommand(deadline);
        } catch (PodzException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareEvent(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new PodzException("OOPS!!! The event task "
                                        + "cannot be empty.");
            }

            String[] eventInfo = inputs[1].split("/", 3);
            if (eventInfo.length != 3) {
                throw new PodzException("OOPS!!! Please check "
                                        + "the event format again.");
            }

            Event event = new Event(eventInfo[0].strip(), 
                                    eventInfo[1].replaceFirst("from", "").strip(),
                                    eventInfo[2].replaceFirst("to", "").strip());
            return new EventCommand(event);
        } catch (PodzException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareDelete(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new PodzException("OOPS!!! The delete command "
                                        + "cannot be empty.");
            }

            int index = parseTaskIndex(inputs[1]);
            return new DeleteCommand(index);
        } catch (PodzException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareFind(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new PodzException("Wrong find command format!!");
            }

            String searchKeyword = inputs[1];
            return new FindCommand(searchKeyword);
        } catch (PodzException e) {
            return new IncorrectCommand(e);
        }
    }

    private Command prepareUnknown() {
        return new IncorrectCommand(
            new PodzException(
                "ERROR!! The system did not recognize the command you entered."));
    }
}
