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

    /**
     * Parses the user input and generates a corresponding command.
     *
     * @param input The user input to be parsed.
     * @return A Command object based on the parsed input.
     */
    public static Command parse(String input) {

        Type type = Type.UNKNOWN;
        String[] inputArray = input.split(" ", 2);
        String stringHeader = inputArray[0].toLowerCase();

        switch (stringHeader) {
        case "find":
            type = Type.FIND;
            break;
        case "list":
            type = Type.LIST;
            break;
        case "mark":
            type = Type.MARK;
            break;
        case "unmark":
            type = Type.UNMARK;
            break;
        case "delete":
            type = Type.DELETE;
            break;
        case "todo":
            type = Type.TODO;
            break;
        case "deadline":
            type = Type.DEADLINE;
            break;
        case "event":
            type = Type.EVENT;
            break;
        case "help":
            type = Type.HELP;
            break;
        case "bye":
        case "exit":
            type = Type.BYE;
            break;
        default:
            type = Type.UNKNOWN;
        }

        // commands with no argument: BYE / LIST / UNKNOWN
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
                return new InvalidCommand("Sorry! Not sure what you're referring to \n"
                                            + "Are you too stressed? \n"
                                                + "Type 'help' to view the list of supported commands!\n");
            }
        }

        // check for following input argument
        if (inputArray.length < 2) {
            try {
                throw new InvalidTaskInputException("command contains no argument");
            } catch (InvalidTaskInputException e) {
                return new InvalidCommand("Please input an argument! \n [command] [argument]\n");
            }
        }

        String stringBody = inputArray[1];

        if (type == Type.MARK) {
            if (isNumeric(stringBody)) {
                int index = Integer.parseInt(stringBody);
                return new MarkUnmarkCommand(true, index);
            } else {
                return new InvalidCommand("Input a valid number to mark! \n Usage: mark [int]\n");
            }
        } else if (type == Type.UNMARK) {
            if (isNumeric(stringBody)) {
                int index = Integer.parseInt(stringBody);
                return new MarkUnmarkCommand(false, index);
            } else {
                return new InvalidCommand("Input a valid number to unmark! \n Usage: unmark [int]\n");
            }
        } else if (type == Type.DELETE) {
            if (isNumeric(stringBody)) {
                int index = Integer.parseInt(stringBody);
                return new DeleteCommand(index);
            } else {
                return new InvalidCommand("Input a valid number to delete! \n Usage: delete [int]\n");
            }
        } else if (type == Type.TODO) {
            Task task = new Todo(stringBody);
            return new AddCommand(task);
        } else if (type == Type.DEADLINE) {
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
        } else if (type == Type.EVENT) {
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
        } else if (type == Type.FIND) {
            return new FindCommand(stringBody);
        }

        // Handle unrecognized commands
        assert type != Type.UNKNOWN : "Unknown command type";
        return new InvalidCommand("General Error! This line should not be reached.");
    }

    private static Boolean isNumeric(String string) {
        return string.matches("\\d+");
    }

}
