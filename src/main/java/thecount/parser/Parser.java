package thecount.parser;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import thecount.exception.TheCountException;
import thecount.storage.Storage;
import thecount.task.Deadline;
import thecount.task.Event;
import thecount.task.TaskList;
import thecount.task.ToDo;
import thecount.ui.Goodbye;
import thecount.ui.Reply;

/**
 * Parses user input and executes corresponding commands.
 */
public class Parser {
    private TaskList tasks;
    private Scanner scanner;
    private Storage loader;

    /**
     * Represents the types of commands.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND,
    }

    /**
     * Constructs a Parser object with the task list and storage.
     *
     * @param tasks The task list to be managed.
     * @param loader The storage instance for loading and saving tasks.
     */
    public Parser(TaskList tasks, Storage loader) {
        this.tasks = tasks;
        this.scanner = new Scanner(System.in);
        this.loader = loader;
    }

    /**
     * Parses user input and executes corresponding commands.
     */
    public String parse(String userInput) {
        assert userInput != null : "User input must not be null";

        // Checks for BYE command
        while (true) {
            switch (getCommandType(userInput)) {
            case BYE:
                new Goodbye().displayMessage();
                scanner.close();
                System.exit(0);
                break;
            case LIST:
                return tasks.printList();
            case MARK:
                return handleMarkTask(userInput, tasks);
            case UNMARK:
                return handleUnmarkTask(userInput, tasks);
            case TODO:
                return handleTodoTask(userInput, tasks);
            case DEADLINE:
                return handleDeadlineTask(userInput, tasks);
            case EVENT:
                return handleEventTask(userInput, tasks);
            case DELETE:
                return handleDeleteTask(userInput, tasks);
            case FIND:
                return handleFindTask(userInput, tasks);
            case INVALID:
                return handleInvalidCommand();
            default:
                break;
            }
            loader.write(tasks);
            userInput = scanner.nextLine();
        }
    }
    private static CommandType getCommandType(String userInput) {
        String commandName = userInput.split("\\s+")[0].toUpperCase();
        try {
            return CommandType.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }

    private static String handleMarkTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
            return tasks.markTask(taskNumber, true);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return handleException("Please put a number. I can't count that!");
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private static String handleUnmarkTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
            return tasks.unmarkTask(taskNumber, true);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return handleException("Please put a number. I can't count that!");
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private static String handleTodoTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, " ");
            ToDo todo = new ToDo(info);
            tasks.add(todo);
            return todo.displayMessage(tasks.length());
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private static String handleDeadlineTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, "/by");
            String deadlineTime = getTaskTime(userInput, "/by", "deadline");
            Deadline deadline = new Deadline(info, deadlineTime);
            tasks.add(deadline);
            return deadline.displayMessage(tasks.length());
        } catch (TheCountException e) {
            return handleException(e, "Example: deadline assignment /by 2021-12-31");
        } catch (DateTimeParseException e) {
            return handleException("Please enter date in the format yyyy-MM-dd.");
        }
    }

    private static String handleEventTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, "/from");
            String startTime = getStartTime(userInput);
            String endTime = getTaskTime(userInput, "/to", "end time");
            Event event = new Event(info, startTime, endTime);
            tasks.add(event);
            return event.displayMessage(tasks.length());
        } catch (TheCountException e) {
            return handleException(e, "Example: event meeting /from 2pm /to 4pm");
        }
    }

    private static String handleDeleteTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
            return tasks.deleteTask(taskNumber);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | TheCountException e) {
            return handleException(e);
        }
    }

    private static String handleInvalidCommand() {
        try {
            throw new TheCountException("WHAT?! I can't count that! Try another command!");
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private static String handleFindTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            String keyword = userInput.split("\\s+", 2)[1].trim();
            return tasks.findTask(keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            return handleException("Please enter a keyword to search for.");
        }
    }

    private static String getTaskInfo(String userInput, String delimiter) throws TheCountException {
        try {
            String info;
            if (delimiter.equals(" ")) {
                info = userInput.split("\\s+", 2)[1].trim();
            } else {
                info = userInput.split("\\s+", 2)[1].split(delimiter)[0].trim();
            }
            if (info.isEmpty()) {
                // Throw an the_count.exception if the_count.task information is not provided
                throw new TheCountException("Description of activity cannot be empty.");
            }
            return info;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Description of activity cannot be empty.");
        }
    }

    private static String getTaskTime(String userInput, String delimiter, String timeType) throws TheCountException {
        try {
            return userInput.split(delimiter)[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Please fill in " + timeType + ".");
        }
    }

    private static String getStartTime(String userInput) throws TheCountException {
        try {
            return userInput.split("/from")[1].split("/to")[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Please fill in start time.");
        }
    }

    private static String handleException(Exception e) {
        Reply errorMsg = new Reply(e.getMessage());
        return errorMsg.displayMessage();
    }

    private static String handleException(Exception e, String additionalMessage) {
        Reply errorMsg = new Reply(e.getMessage() + "\n" + additionalMessage);
        return errorMsg.displayMessage();
    }

    private static String handleException(String additionalMessage) {
        Reply errorMsg = new Reply(additionalMessage);
        return errorMsg.displayMessage();
    }
}
