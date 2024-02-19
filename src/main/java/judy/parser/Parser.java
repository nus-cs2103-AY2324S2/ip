package judy.parser;

import judy.exceptions.DukeException;
import judy.commands.Command;
import judy.task.*;
import judy.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing user input and converting it into corresponding Command objects.
 */
public class Parser {
    private final String userInput;
    private final TaskList taskList;

    /**
     * Constructs a Parser with the specified user input and task list.
     *
     * @param userInput The user input to be parsed.
     * @param taskList  The task list on which the commands operate.
     */
    public Parser(String userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @return The Command object representing the parsed user input.
     * @throws DukeException If an error occurs during the parsing process.
     */
    public Command parse() throws DukeException {
        String[] commandParts = userInput.split(" ",2);
        Command command;
        switch (commandParts[0]) {
            case ListTaskCommand.COMMAND_WORD:
                command = new ListTaskCommand(taskList);
                break;
            case MarkTaskCommand.COMMAND_WORD:
                command = handleMark(commandParts);
                break;
            case UnmarkTaskCommand.COMMAND_WORD:
                command = handleUnmark(commandParts);
                break;
            case DeleteTaskCommand.COMMAND_WORD:
                return handleDelete(commandParts);
            case AddTaskCommand.TODO:
                Task todo = handleTodo(commandParts);
                command = new AddTaskCommand(todo, this.taskList);
                break;
            case AddTaskCommand.DEADLINE:
                Task deadline = handleDeadline(commandParts);
                command = new AddTaskCommand(deadline, this.taskList);
                break;
            case AddTaskCommand.EVENT:
                Task event = handleEvent(commandParts);
                command = new AddTaskCommand(event, this.taskList);
                break;
            case FindTaskCommand.COMMAND_WORD:
                command = handleFind(commandParts);
                break;
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand();
                break;
            case HelpCommand.COMMAND_WORD:
                command = new HelpCommand();
                break;
            default:
                command = new InvalidCommand();
        }
        return command;
    }

    /**
     * Parses a mark task input.
     *
     * @param commandParts The parts of the user input after splitting by space.
     * @return a MarkTaskCommand based on user's input.
     * @throws DukeException if user entered empty or invalid index.
     */

    private Command handleMark(String[] commandParts) throws DukeException {
        try {
            int taskId = Integer.parseInt(commandParts[1]) - 1;
            try {
                return new MarkTaskCommand(taskId, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" Invalid task index. Type 'list' to list out your tasks. ");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(" The index you've input is not an integer. ");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" The index of task cannot be empty.");
        }
    }

    /**
     * Parses a unmark task input
     *
     * @param commandParts The parts of the user input after splitting by space.
     * @return a UnmarkTaskCommand based on user's input.
     * @throws DukeException if user entered empty or invalid index.
     */
    private Command handleUnmark(String[] commandParts) throws DukeException {
        try {
            int taskId = Integer.parseInt(commandParts[1]) - 1;
            try {
                return new UnmarkTaskCommand(taskId, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" Invalid task index. Type 'list' to list out your tasks. ");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(" The task index you've input is not an integer. ");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" The index of task cannot be empty.");
        }
    }

    /**
     * Parses a delete task input
     *
     * @param commandParts The parts of the user input after splitting by space.
     * @return a DeleteCommand based on user's input.
     * @throws DukeException if user entered empty or invalid index.
     */
    private Command handleDelete(String[] commandParts) throws DukeException {
            try {
                int taskIndex = Integer.parseInt(commandParts[1].trim()) - 1;
                try {
                    return new DeleteTaskCommand(taskIndex, taskList);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(" Invalid task index. Type 'list' to list out your tasks. ");
                }
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" The index of task cannot be empty. ");
            } catch (NumberFormatException e) {
                throw new DukeException(" The task index you've input is not an integer. ");
            }
    }

    /**
     * Parses a todo input.
     *
     * @return a Todo based on user's input.
     * @throws DukeException if user left the description empty.
     */
    private static Todo handleTodo(String[] todo) throws DukeException {
        if(todo.length != 2 || todo[1].isEmpty()) {
            throw new DukeException(" The description of a todo cannot be empty :c \n" +
                    " (Eg format: todo <Description> )");
        }
        return new Todo(todo[1]);
    }

    /**
     * Parses a deadline input.
     *
     * @return a Deadline based on user's input.
     * @throws DukeException if user left the description empty or entered invalid format.
     */
    private static Deadline handleDeadline(String[] deadline) throws DukeException {
        if (deadline.length != 2 || deadline[1].isEmpty()) {
            throw new DukeException(" The description of a deadline cannot be empty.");
        } else {
            String[] parts = deadline[1].split("/by ", 2);
            if (parts.length == 2) {
                String taskDescription = parts[0].trim();
                String by = parts[1].trim();
                LocalDateTime byDateTime;
                try {
                    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    byDateTime = LocalDateTime.parse(by, pattern);
                } catch (DateTimeParseException e) {
                    throw new DukeException(" Invalid date format! Please use yyyy-MM-dd HHmm.");
                }
                return new Deadline(taskDescription, byDateTime);
            } else {
                throw new DukeException(" Invalid format :c \n" +
                        " (Eg format: deadline <Description> /by yyyy-MM-dd HHmm)");
            }
        }
    }

    /**
     * Parses an event input.
     *
     * @return an Event based on user's input.
     * @throws DukeException if user left the description empty or entered invalid format.
     */
    private static Event handleEvent(String[] event) throws DukeException {
        if (event.length != 2 || event[1].isEmpty()) {
            throw new DukeException(" The description of an event cannot be empty.");
        } else {
            String[] eventParts = event[1].split("/from ", 2);
            if (eventParts.length == 2) {
                String taskDescription = eventParts[0].trim();
                String[] eventDetails = eventParts[1].split("/to ");
                if (eventDetails.length == 2) {
                    String from = eventDetails[0].trim();
                    String to = eventDetails[1].trim();
                    LocalDateTime fromDateTime;
                    LocalDateTime toDateTime;
                    try {
                        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        fromDateTime = LocalDateTime.parse(from, pattern);
                        toDateTime = LocalDateTime.parse(to, pattern);
                    } catch (DateTimeParseException e) {
                        throw new DukeException(" Invalid date/time format! Please use yyyy-MM-dd HHmm. ");
                    }
                    return new Event(taskDescription, fromDateTime, toDateTime);
                } else {
                    throw new DukeException(" Oops! Invalid format :c \n " +
                            "(Try this: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm)");
                }
            } else {
                throw new DukeException(" Oops! Invalid format :c \n " +
                        " (Try this: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm)");
            }
        }
    }

    /**
     * Parses a find task input.
     *
     * @return a FindTaskCommand based on user's input.
     * @throws DukeException if user left the description empty.
     */
    private FindTaskCommand handleFind(String[] input) throws DukeException {
        if (input.length != 2 || input[1].isEmpty()) {
            throw new DukeException("The description of find cannot be empty.");
        }
        return new FindTaskCommand(this.taskList, input[1]);
    }
}
