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
import dave.commands.TaskRemindersCommand;
import dave.commands.ToggleTaskDoneCommand;
import dave.common.Messages;
import dave.exceptions.ChatbotException;
import dave.exceptions.EmptyTaskException;
import dave.exceptions.InvalidInputException;

public class Parser {
    private enum CommandType {
        LIST,
        FIND,
        REMIND,
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
            commandStr = CommandType.valueOf(inputArr[0].toUpperCase()); // Solution inspired by https://www.tutorialspoint.com/java/lang/enum_valueof.htm
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
                    return parseFindTaskCommand(input);
                case REMIND:
                    assert commandStr.equals(CommandType.REMIND);
                    return new TaskRemindersCommand();
                case DELETE:
                    assert commandStr.equals(CommandType.DELETE);
                    return parseDeleteTaskCommand(inputArr[1]);
                case MARK:
                    assert commandStr.equals(CommandType.MARK);
                    return parseToggleTaskDoneCommand(inputArr[1], true);
                case UNMARK:
                    assert commandStr.equals(CommandType.UNMARK);
                    return parseToggleTaskDoneCommand(inputArr[1], false);
                case TODO:
                    assert commandStr.equals(CommandType.TODO);
                    return parseAddTodoCommand(input);
                case DEADLINE:
                    assert commandStr.equals(CommandType.DEADLINE);
                    return parseAddDeadlineCommand(input);
                case EVENT:
                    assert commandStr.equals(CommandType.EVENT);
                    return parseAddEventCommand(input);
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

    /**
     * Parses a find task command.
     * 
     * @param command The command string.
     * @return A find task command using the parsed keyword. If invalid keyword,
     *         return an invalid command.
     * @throws ChatbotException If invalid keyword is given.
     */
    public static Command parseFindTaskCommand(String command) throws ChatbotException {
        try {
            String keyword = command.substring(5);
            return new FindTaskCommand(keyword);
        } catch (Exception exc) {
            return new InvalidCommand(new EmptyTaskException(Messages.INVALID_KEYWORD_MESSAGE));
        }
    }

    /**
     * Parses a delete task command.
     * 
     * @param taskNumber The task number as a String.
     * @return A delete task command using the task number. If invalid task number,
     *         return an invalid command.
     * @throws ChatbotException If invalid task number is given.
     */
    public static Command parseDeleteTaskCommand(String taskNumber) throws ChatbotException {
        int deleteNumber = Integer.parseInt(taskNumber) - 1;
        try {
            return new DeleteTaskCommand(deleteNumber);
        } catch (Exception exc) {
            return new InvalidCommand(new ChatbotException(exc.getMessage()));
        }
    }

    /**
     * Parses a toggle task done command.
     * 
     * @param taskNumber The task number as a String.
     * @param isDone     The boolean to mark a task. If true, task is marked.
     *                   Otherwise, task is unmarked.
     * @return A toggle task done command using the task number. If invalid task
     *         number, return an invalid command.
     * @throws ChatbotException If invalid task number is given.
     */
    public static Command parseToggleTaskDoneCommand(String taskNumber, boolean isDone) throws ChatbotException {
        int toggleNumber = Integer.parseInt(taskNumber) - 1;
        try {
            return new ToggleTaskDoneCommand(toggleNumber, isDone);
        } catch (Exception exc) {
            return new InvalidCommand(new ChatbotException(exc.getMessage()));
        }
    }

    /**
     * Parses an add todo command.
     * 
     * @param command The command string.
     * @return An add todo command. If invalid task name, return an invalid command.
     * @throws ChatbotException If invalid task name is given.
     */
    public static Command parseAddTodoCommand(String command) throws ChatbotException {
        try {
            String todoName = command.substring(5);
            return new AddTodoCommand(todoName);
        } catch (Exception exc) {
            return new InvalidCommand(
                    new EmptyTaskException(Messages.INVALID_TODO_NAME));
        }
    }

    /**
     * Parses an add deadline command.
     * 
     * @param command The command string.
     * @return An add deadline command. If invalid task name or deadline, return an
     *         invalid command.
     * @throws ChatbotException If invalid task name or deadline is given.
     */
    public static Command parseAddDeadlineCommand(String command) throws ChatbotException {
        try {
            int idxDeadline = command.indexOf("/by");
            String deadlineName = command.substring(9, idxDeadline - 1);
            String deadline = command.substring(idxDeadline + "/by ".length());
            return new AddDeadlineCommand(deadlineName, deadline);
        } catch (Exception exc) {
            return new InvalidCommand(
                    new EmptyTaskException(Messages.INVALID_DEADLINE_NAME_OR_DEADLINE));
        }
    }

    /**
     * Parses an add event command.
     * 
     * @param command The command string.
     * @return An add event command. If invalid task name or event duration, return
     *         an invalid command.
     * @throws ChatbotException If invalid task name or event duration is given.
     */
    public static Command parseAddEventCommand(String command) throws ChatbotException {
        try {
            int idxFrom = command.indexOf("/from");
            int idxTo = command.indexOf("/to");
            String eventName = command.substring(6, idxFrom - 1);
            String from = command.substring(idxFrom + "/from ".length(), idxTo - 1);
            String to = command.substring(idxTo + "/to ".length());
            return new AddEventCommand(eventName, from, to);
        } catch (Exception exc) {
            return new InvalidCommand(
                    new EmptyTaskException(Messages.INVALID_EVENT_NAME_OR_DURATION));
        }
    }

}
