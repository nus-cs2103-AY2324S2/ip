package ellie;

import ellie.command.AddCommand;
import ellie.command.Command;
import ellie.command.DeleteCommand;
import ellie.command.ExitCommand;
import ellie.command.FindCommand;
import ellie.command.HelpCommand;
import ellie.command.InvalidCommand;
import ellie.command.ListCommand;
import ellie.command.MarkUnmarkCommand;
import ellie.exception.InvalidTaskInputException;
import ellie.exception.UnknownInputException;
import ellie.task.Deadline;
import ellie.task.Event;
import ellie.task.Task;
import ellie.task.Todo;

/**
 * The Parser class is responsible for parsing user input and generating corresponding commands.
 * It recognizes different command types and arguments, creating appropriate command objects.
 */
public class Parser {

    /**
     * Enum representing different command types.
     */
    enum Type {
        FIND,
        MARK,
        UNMARK,
        LIST,
        DEADLINE,
        EVENT,
        TODO,
        UNKNOWN,
        HELP,
        BYE,
        DELETE,

    }

    public static final String UNKNOWN_COMMAND_MESSAGE = "Sorry! Not sure what you're referring to \n"
                                                            + "Are you too stressed? \n"
                                                            + "Type 'help' to view the list of supported commands!\n";

    /**
     * Parses the user input and generates a corresponding command.
     *
     * @param input The user input to be parsed.
     * @return A Command object based on the parsed input.
     */
    public static Command parse(String input) {

        Type type;
        Command command;
        String[] inputArray = input.split(" ", 2);
        String stringHeader = inputArray[0].toLowerCase();

        // Get Command Type
        type = parseCommandType(stringHeader);

        // commands with no argument: BYE / LIST / UNKNOWN
        command = parseCommandsWithNoArguments(type);
        if (command != null) {
            return command;
        }

        // check for following input argument
        command = parseCommandForAtLeastTwoArguments(inputArray.length);
        if (command != null) {
            return command;
        }

        String stringBody = inputArray[1];

        switch (type) {
        case MARK:
            return parseMarkCommand(stringBody);
        case UNMARK:
            return parseUnmarkCommand(stringBody);
        case DELETE:
            return parseDeleteCommand(stringBody);
        case TODO:
            return parseTodoCommand(stringBody);
        case DEADLINE:
            return parseDeadlineCommand(stringBody);
        case EVENT:
            return parseEventCommand(stringBody);
        case FIND:
            return parseFindCommand(stringBody);
        default:
            break;
        }

        // Handle unrecognized commands
        assert type != Type.UNKNOWN : "Unknown command type";
        return new InvalidCommand("General Error! This line should not be reached.");
    }

    private static Type parseCommandType(String stringHeader) {
        switch (stringHeader) {
        case "find":
            return Type.FIND;
        case "list":
            return Type.LIST;
        case "mark":
            return Type.MARK;
        case "unmark":
            return Type.UNMARK;
        case "delete":
            return Type.DELETE;
        case "todo":
            return Type.TODO;
        case "deadline":
            return Type.DEADLINE;
        case "event":
            return Type.EVENT;
        case "help":
            return Type.HELP;
        case "bye":
        case "exit":
            return Type.BYE;
        default:
            return Type.UNKNOWN;
        }
    }

    private static Command parseCommandsWithNoArguments(Type type) {
        if (type == Type.BYE) {
            return new ExitCommand();
        } else if (type == Type.LIST) {
            return new ListCommand();
        } else if (type == Type.HELP) {
            // Extension: C-Help
            return new HelpCommand();
        } else if (type == Type.UNKNOWN) {
            try {
                throw new UnknownInputException("Command Unknown or Missing");
            } catch (UnknownInputException e) {
                return new InvalidCommand(UNKNOWN_COMMAND_MESSAGE);
            }
        }

        return null;
    }

    private static Command parseCommandForAtLeastTwoArguments(int inputArrayLength) {
        if (inputArrayLength < 2) {
            try {
                throw new InvalidTaskInputException("command contains no argument");
            } catch (InvalidTaskInputException e) {
                return new InvalidCommand("Please input an argument! \n [command] [argument]\n");
            }
        }
        return null;
    }

    private static Command parseMarkCommand(String stringBody) {
        if (isNumeric(stringBody)) {
            int index = Integer.parseInt(stringBody);
            return new MarkUnmarkCommand(true, index);
        } else {
            return new InvalidCommand("Input a valid number to mark! \n Usage: mark [int]\n");
        }
    }

    private static Command parseUnmarkCommand(String stringBody) {
        if (isNumeric(stringBody)) {
            int index = Integer.parseInt(stringBody);
            return new MarkUnmarkCommand(false, index);
        } else {
            return new InvalidCommand("Input a valid number to unmark! \n Usage: unmark [int]\n");
        }
    }

    private static Command parseDeleteCommand(String stringBody) {
        if (isNumeric(stringBody)) {
            int index = Integer.parseInt(stringBody);
            return new DeleteCommand(index);
        } else {
            return new InvalidCommand("Input a valid number to delete! \n Usage: delete [int]\n");
        }
    }

    private static Command parseTodoCommand(String stringBody) {
        return new AddCommand(new Todo(stringBody));
    }

    private static Command parseDeadlineCommand(String stringBody) {
        if (!stringBody.contains("/by")) {
            return new InvalidCommand("Please add a due date for your dateline using '/by'!");
        } else {
            String[] deadlineArray = stringBody.split("/by");
            String event = deadlineArray[0].trim();
            String dueDate = deadlineArray[1].trim();
            if (event.isEmpty()) {
                return new InvalidCommand("Please add event name.");
            } else if (dueDate.isEmpty()) {
                return new InvalidCommand("Please add a deadline!");
            } else {
                Task task = new Deadline(event, dueDate);
                return new AddCommand(task);
            }
        }
    }

    private static Command parseEventCommand(String stringBody) {
        if (!stringBody.contains("/from")) {
            return new InvalidCommand("Please add a start date for your event using '/from'!");
        } else if (!stringBody.contains("/to")) {
            return new InvalidCommand("Please add an end date for your event using '/to'!");
        } else {
            String[] deadlineArray = stringBody.split("/from");
            String event = deadlineArray[0].trim();
            String eventDuration = deadlineArray[1];
            if (event.isEmpty()) {
                return new InvalidCommand("Please add event name.");
            } else if (event.contains("/end")) {
                return new InvalidCommand("Please place /end [end time] after /from [start time]!");
            } else {
                String[] duration = eventDuration.split("/to");
                Task task = new Event(event, duration[0].trim(), duration[1].trim());
                return new AddCommand(task);
            }
        }
    }

    private static Command parseFindCommand(String stringBody) {
        return new FindCommand(stringBody);
    }

    private static Boolean isNumeric(String string) {
        return string.matches("\\d+");
    }

}
