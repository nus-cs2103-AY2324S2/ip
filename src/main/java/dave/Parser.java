package dave;

import dave.commands.AddTodoCommand;
import dave.commands.AddDeadlineCommand;
import dave.commands.AddEventCommand;
import dave.commands.Command;
import dave.commands.DeleteTaskCommand;
import dave.commands.ExitCommand;
import dave.commands.FindTaskCommand;
import dave.commands.InvalidCommand;
import dave.commands.ListTasksCommand;
import dave.commands.ToggleTaskDoneCommand;

import dave.exceptions.ChatbotException;
import dave.exceptions.EmptyTaskException;
import dave.exceptions.InvalidInputException;

public class Parser {
    private enum CommandType {
        LIST,
        FIND,
        DELETE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        BYE
    }

    /**
     * Parses inputs from user.
     * 
     * @param input User input.
     * @return A command that executes operations according to the user's input.
     * @throws ChatbotException If unrecognisable or invalid input is given.
     */
    public static Command parse(String input) throws ChatbotException {
        String[] inputArr = input.split(" ");

        CommandType commandStr = null;

        try {
            commandStr = CommandType.valueOf(inputArr[0].toUpperCase());
        } catch (IllegalArgumentException exc) {
            return new InvalidCommand(new InvalidInputException());
        } finally {
            if (commandStr != null) {
                switch (commandStr) {
                case LIST:
                    assert commandStr.equals(CommandType.LIST);
                    return new ListTasksCommand();
                case FIND:
                    assert commandStr.equals(CommandType.FIND);
                    try {
                        String keyword = input.substring(5);
                        return new FindTaskCommand(keyword);
                    } catch (Exception exc) {
                        return new InvalidCommand(new EmptyTaskException("Dave cannot find a task without a keyword."
                        + "\nPlease help Dave by entering your search as follows:\n" +
                        "\nfind <keyword>"));
                    }
                case DELETE:
                    assert commandStr.equals(CommandType.DELETE);
                    int deleteNumber = Integer.parseInt(inputArr[1]) - 1;
                    try {
                        return new DeleteTaskCommand(deleteNumber);
                    } catch (Exception exc) {
                        return new InvalidCommand(new ChatbotException(exc.getMessage()));
                    }
                case MARK:
                    assert commandStr.equals(CommandType.MARK);
                    int markNumber = Integer.parseInt(inputArr[1]) - 1;
                    return new ToggleTaskDoneCommand(markNumber, true);
                case UNMARK:
                    assert commandStr.equals(CommandType.UNMARK);
                    int unmarkNumber = Integer.parseInt(inputArr[1]) - 1;
                    return new ToggleTaskDoneCommand(unmarkNumber, false);
                case TODO:
                    assert commandStr.equals(CommandType.TODO);
                    try {
                        String todoName = input.substring(5);
                        return new AddTodoCommand(todoName);
                    } catch (Exception exc) {
                        return new InvalidCommand(
                                new EmptyTaskException("Dave cannot record a TODO task without a name."
                                        + "\nPlease help Dave by entering your TODO name as follows:\n"
                                        + "\ntodo <name>"));
                    }
                case DEADLINE:
                    assert commandStr.equals(CommandType.DEADLINE);
                    try {
                        int idxDeadline = input.indexOf("/by");
                        String deadlineName = input.substring(9, idxDeadline - 1);
                        String deadline = input.substring(idxDeadline + "/by ".length());
                        return new AddDeadlineCommand(deadlineName, deadline);
                    } catch (Exception exc) {
                        return new InvalidCommand(
                                new EmptyTaskException(
                                        "Dave cannot record a DEADLINE task without a name/deadline."
                                        + "\nPlease help Dave by entering your DEADLINE task as follows:\n"
                                        + "\ndeadline <name> /by dd-mm-yyyy hhmm"));
                    }
                case EVENT:
                    assert commandStr.equals(CommandType.EVENT);
                    try {
                        int idxFrom = input.indexOf("/from");
                        int idxTo = input.indexOf("/to");
                        String eventName = input.substring(6, idxFrom - 1);
                        String from = input.substring(idxFrom + "/from ".length(), idxTo - 1);
                        String to = input.substring(idxTo + "/to ".length());
                        return new AddEventCommand(eventName, from, to);
                    } catch (Exception exc) {
                        return new InvalidCommand(
                                new EmptyTaskException("Dave cannot record an EVENT task without a name/duration."
                                        + "\nPlease help Dave by entering your EVENT task as follows:\n"
                                        + "\nevent <name> /from dd-mm-yyy hhmm /to dd-mm-yyyy hhmm"));
                    }
                case BYE:
                    assert commandStr.equals(CommandType.BYE);
                    return new ExitCommand();

                default:
                    return new InvalidCommand(new InvalidInputException());
                }
            }
        }
        return new InvalidCommand(new InvalidInputException());
    }

}
