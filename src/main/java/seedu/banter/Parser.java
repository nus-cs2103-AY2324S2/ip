package seedu.banter;

import java.time.LocalDateTime;

import seedu.banter.enums.CommandType;
import seedu.banter.errors.Errors;
import seedu.banter.errors.InvalidBanterUsageError;
import seedu.banter.tasks.TaskList;
import seedu.banter.ui.Card;
import seedu.banter.ui.Ui;
import seedu.banter.utilities.DateTime;


/**
 * Represents a parser that parses user input and functions as the app controller.
 */
public class Parser {
    // Constants
    private static final String SEPARATOR = " ";
    private static final String DEADLINE_DUE_DATE = "/by";
    private static final String EVENT_START = "/from";
    private static final String EVENT_END = "/to";

    // Attributes
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a new Parser object.
     * @param storage Storage object that handles loading and saving of tasks.
     */
    public Parser(Storage storage) {
        this.storage = storage;
        taskList = this.storage.loadTaskList();
    }

    // Methods
    /**
     * Prints the greeting message.
     */
    public void printGreetMessage() {
        System.out.println(Ui.BANTER_LOGO);
        Ui.GREET_MESSAGE.print();
    }

    private void printExitMessage() {
        Ui.EXIT_MESSAGE.print();
    }

    /**
     * Responds to user input until the user exits the app, then print exit message.
     */
    public String respondToUser(String input) {
        try {
            CommandType command = getCommandType(input);
            switch (command) {
            case LIST:
                return parseList();
            case MARK:
                return parseMark(input);
            case UNMARK:
                return parseUnmark(input);
            case TODO:
                return parseTodo(input);
            case DEADLINE:
                return parseDeadline(input);
            case EVENT:
                return parseEvent(input);
            case DELETE:
                return parseDelete(input);
            case FIND:
                return parseFind(input);
            default:
                assert false : "Invalid command type parsed";
            }
        } catch (InvalidBanterUsageError e) {
            Card errorMessage = new Card(e.getMessage());
            return errorMessage.getString();
        }

        return "";
    }

    private CommandType getCommandType(String input) throws InvalidBanterUsageError {
        try {
            return CommandType.valueOf(input.split(SEPARATOR)[0].toUpperCase());
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.INVALID_COMMAND_ERROR;
        }
    }

    private String parseList() {
        Card taskListMessage = new Card(taskList.toString());
        return taskListMessage.getString();
    }

    private String parseTodo(String input) throws InvalidBanterUsageError {
        String[] words = getWords(input);
        if (words.length == 1) {
            throw Errors.MISSING_TODO_DESCRIPTION_ERROR;
        }
        String description = joinWords(words, 1, words.length - 1);
        Card taskAddedMessage = new Card(taskList.addNewTodo(description));
        storage.saveTaskList(taskList);
        return taskAddedMessage.getString();
    }

    private String parseDeadline(String input) throws InvalidBanterUsageError {
        String[] words = getWords(input);

        int indexOfDueDate = indexOf(words, DEADLINE_DUE_DATE);
        if (indexOfDueDate == -1) {
            throw Errors.MISSING_DEADLINE_DUE_DATE_ERROR;
        }
        String dueDateStr = joinWords(words, indexOfDueDate + 1, words.length - 1);
        if (dueDateStr.isEmpty()) {
            throw Errors.MISSING_DEADLINE_DUE_DATE_ERROR;
        }
        LocalDateTime dueDate = DateTime.getDateTimeFromUserInput(dueDateStr);

        String description = joinWords(words, 1, indexOfDueDate - 1);
        if (description.isEmpty()) {
            throw Errors.MISSING_DEADLINE_DESCRIPTION_ERROR;
        }

        Card taskAddedMessage = new Card(taskList.addNewDeadline(description, dueDate));
        storage.saveTaskList(taskList);
        return taskAddedMessage.getString();
    }

    private String parseEvent(String input) throws InvalidBanterUsageError {
        String[] words = getWords(input);

        int indexOfEnd = indexOf(words, EVENT_END);
        if (indexOfEnd == -1) {
            throw Errors.MISSING_EVENT_END_ERROR;
        }
        String endStr = joinWords(words, indexOfEnd + 1, words.length - 1);
        if (endStr.isEmpty()) {
            throw Errors.MISSING_EVENT_END_ERROR;
        }
        LocalDateTime end = DateTime.getDateTimeFromUserInput(endStr);

        int indexOfStart = indexOf(words, EVENT_START);
        if (indexOfStart == -1) {
            throw Errors.MISSING_EVENT_START_ERROR;
        }
        String startStr = joinWords(words, indexOfStart + 1, indexOfEnd - 1);
        if (startStr.isEmpty()) {
            throw Errors.MISSING_EVENT_START_ERROR;
        }
        LocalDateTime start = DateTime.getDateTimeFromUserInput(startStr);

        String description = joinWords(words, 1, indexOfStart - 1);
        if (description.isEmpty()) {
            throw Errors.MISSING_EVENT_DESCRIPTION_ERROR;
        }

        Card taskAddedMessage = new Card(taskList.addNewEvent(description, start, end));
        storage.saveTaskList(taskList);
        return taskAddedMessage.getString();
    }

    private String parseMark(String input) throws InvalidBanterUsageError {
        String[] words = getWords(input);
        int taskNumber;
        try {
            taskNumber = getTaskNumber(words);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.INVALID_MARK_TASK_NUMBER_ERROR;
        }
        Card taskDoneMessage = new Card(taskList.markTaskAsDone(taskNumber));
        storage.saveTaskList(taskList);
        return taskDoneMessage.getString();
    }

    private String parseUnmark(String input) throws InvalidBanterUsageError {
        String[] words = getWords(input);
        int taskNumber;
        try {
            taskNumber = getTaskNumber(words);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.INVALID_UNMARK_TASK_NUMBER_ERROR;
        }
        Card taskUndoneMessage = new Card(taskList.markTaskAsUndone(taskNumber));
        storage.saveTaskList(taskList);
        return taskUndoneMessage.getString();
    }

    private String parseDelete(String input) throws InvalidBanterUsageError {
        String[] words = getWords(input);
        int taskNumber;
        try {
            taskNumber = getTaskNumber(words);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.INVALID_DELETE_TASK_NUMBER_ERROR;
        }
        Card taskDeletedMessage = new Card(taskList.deleteTask(taskNumber));
        storage.saveTaskList(taskList);
        return taskDeletedMessage.getString();
    }

    /**
     * Finds tasks that contain the keyword(s) in the input.
     * @param input
     * @throws InvalidBanterUsageError
     */
    private String parseFind(String input) throws InvalidBanterUsageError {
        String[] words = getWords(input);
        if (words.length == 1) {
            throw Errors.MISSING_KEYWORD_ERROR;
        }
        String keyword = joinWords(words, 1, words.length - 1);
        Card taskFoundMessage = new Card(taskList.findTasks(keyword));
        return taskFoundMessage.getString();
    }


    // Helper methods
    private String[] getWords(String input) {
        return input.split(SEPARATOR);
    }

    private String joinWords(String[] words, int start, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (result.length() != 0) {
                result.append(SEPARATOR);
            }
            result.append(words[i]);
        }
        return result.toString();
    }

    private int indexOf(String[] words, String prefix) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(prefix)) {
                return i;
            }
        }
        return -1;
    }

    private int getTaskNumber(String[] words) throws IndexOutOfBoundsException, IllegalArgumentException {
        return Integer.parseInt(words[1]);
    }
}
