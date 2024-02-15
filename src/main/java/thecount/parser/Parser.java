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
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND, TAG,
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
                return handleGoodbye();
            case LIST:
                return tasks.printList();
            case MARK:
                return handleMarkTask(userInput, tasks);
            case UNMARK:
                return handleUnmarkTask(userInput, tasks);
            case TAG:
                return handleTagTask(userInput, tasks);
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
            userInput = scanner.nextLine();
        }
    }
    private CommandType getCommandType(String userInput) {
        String commandName = userInput.split("\\s+")[0].toUpperCase();
        try {
            return CommandType.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }

    private String handleGoodbye() {
        new Goodbye().displayMessage();
        scanner.close();
        System.exit(0);
        return "";
    }

    private String handleMarkTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);

            String result = tasks.markTask(taskNumber, true);
            loader.write(tasks);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return handleException("Please put a number. I can't count that!");
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private String handleUnmarkTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);

            String result = tasks.unmarkTask(taskNumber, true);
            loader.write(tasks);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return handleException("Please put a number. I can't count that!");
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    /**
     * Handles the tagging of a task with a user-provided tag.
     *
     * @param userInput The user input containing the task number and tag.
     * @param tasks The TaskList instance managing the tasks.
     * @return A message indicating the task has been tagged.
     */
    private String handleTagTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            String[] parts = userInput.split("\\s+", 3);
            int taskNumber = Integer.parseInt(parts[1]);
            String tag = parts.length > 2 ? parts[2].trim() : "";

            if (parts.length < 3 || tag.isEmpty()) {
                throw new TheCountException("Tag cannot be empty. Please provide a tag.");
            }
            String result = tasks.tagTask(taskNumber, tag);
            loader.write(tasks);

            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return handleException("Please put a number. I can't count that!");
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private String handleTodoTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, " ");
            ToDo todo = new ToDo(info);

            tasks.add(todo);
            String result = todo.displayMessage(tasks.length());
            loader.write(tasks);
            return result;
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private String handleDeadlineTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, "/by");
            String deadlineTime = getTaskTime(userInput, "/by", "deadline");
            Deadline deadline = new Deadline(info, deadlineTime);

            tasks.add(deadline);
            String result = deadline.displayMessage(tasks.length());
            loader.write(tasks);
            return result;
        } catch (TheCountException e) {
            return handleException(e, "Example: deadline assignment /by 2021-12-31");
        } catch (DateTimeParseException e) {
            return handleException("Please enter date in the format yyyy-MM-dd.");
        }
    }

    private String handleEventTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, "/from");
            String startTime = getStartTime(userInput);
            String endTime = getTaskTime(userInput, "/to", "end time");
            Event event = new Event(info, startTime, endTime);

            tasks.add(event);
            String result = event.displayMessage(tasks.length());
            loader.write(tasks);
            return result;
        } catch (TheCountException e) {
            return handleException(e, "Example: event meeting /from 2pm /to 4pm");
        }
    }

    private String handleDeleteTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
            String result = tasks.deleteTask(taskNumber);
            loader.write(tasks);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | TheCountException e) {
            return handleException(e);
        }
    }

    private String handleInvalidCommand() {
        try {
            throw new TheCountException("WHAT?! I can't count that! Try another command!");
        } catch (TheCountException e) {
            return handleException(e);
        }
    }

    private String handleFindTask(String userInput, TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            String keyword = userInput.split("\\s+", 2)[1].trim();
            String result = tasks.findTask(keyword);
            loader.write(tasks);
            return result;
        } catch (ArrayIndexOutOfBoundsException e) {
            return handleException("Please enter a keyword to search for.");
        }
    }

    private String getTaskInfo(String userInput, String delimiter) throws TheCountException {
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

    private String getTaskTime(String userInput, String delimiter, String timeType) throws TheCountException {
        try {
            return userInput.split(delimiter)[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Please fill in " + timeType + ".");
        }
    }

    private String getStartTime(String userInput) throws TheCountException {
        try {
            return userInput.split("/from")[1].split("/to")[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Please fill in start time.");
        }
    }

    private String handleException(Exception e) {
        Reply errorMsg = new Reply(e.getMessage());
        return errorMsg.displayMessage();
    }

    private String handleException(Exception e, String additionalMessage) {
        Reply errorMsg = new Reply(e.getMessage() + "\n" + additionalMessage);
        return errorMsg.displayMessage();
    }

    private String handleException(String additionalMessage) {
        Reply errorMsg = new Reply(additionalMessage);
        return errorMsg.displayMessage();
    }
}
