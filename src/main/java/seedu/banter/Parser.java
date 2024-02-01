package seedu.banter;

import seedu.banter.enums.CommandType;
import seedu.banter.errors.InvalidBanterUsageError;
import seedu.banter.ui.Card;
import seedu.banter.tasks.TaskList;

import java.time.LocalDateTime;
import java.util.Scanner;
import seedu.banter.errors.Errors;
import seedu.banter.ui.Ui;
import seedu.banter.utilities.DateTime;


/**
 * Represents a parser that parses user input and functions as the app controller.
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;

    // Constants
    private static final String SEPARATOR = " ";
    private static final String DEADLINE_DUE_DATE = "/by";
    private static final String EVENT_START = "/from";
    private static final String EVENT_END = "/to";
    
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
    public void respondUntilExit() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            CommandType command = null;
            String input = scanner.nextLine();
            try {
                command = getCommandType(input);
                switch (command) {
                    case BYE:
                        printExitMessage();
                        break;
                    case LIST:
                        parseList();
                        break;
                    case MARK:
                        parseMark(input);
                        break;
                    case UNMARK:
                        parseUnmark(input);
                        break;
                    case TODO:
                        parseTodo(input);
                        break;
                    case DEADLINE:
                        parseDeadline(input);
                        break;
                    case EVENT:
                        parseEvent(input);
                        break;
                    case DELETE:
                        parseDelete(input);
                        break;
                    case FIND:
                        parseFind(input);
                        break;
                    default:
                        throw Errors.InvalidCommandError;
                }
            } catch (InvalidBanterUsageError e) {
                Card errorMessage = new Card(e.getMessage());
                errorMessage.print();
            }

            if (command == CommandType.BYE) {
                break;
            }
        }

        scanner.close();
    }

    private CommandType getCommandType(String input) throws InvalidBanterUsageError {
        try {
            return CommandType.valueOf(input.split(SEPARATOR)[0].toUpperCase());
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.InvalidCommandError;
        }
    }

    private void parseList() {
        Card taskListMessage = new Card(taskList.toString());
        taskListMessage.print();
    }

    private void parseTodo(String input) throws InvalidBanterUsageError {
        String[] tokens = getTokens(input);
        if (tokens.length == 1) {
            throw Errors.MissingTodoDescriptionError;
        }
        String description = joinTokens(tokens, 1, tokens.length - 1);
        Card taskAddedMessage = new Card(taskList.loadTodo(description));
        storage.saveTaskList(taskList);
        taskAddedMessage.print();
    }

    private void parseDeadline(String input) throws InvalidBanterUsageError {
        String[] tokens = getTokens(input);

        int indexOfDueDate = indexOf(tokens, DEADLINE_DUE_DATE);
        if (indexOfDueDate == -1) {
            throw Errors.MissingDeadlineDueDateError;
        }
        String dueDateStr = joinTokens(tokens, indexOfDueDate + 1, tokens.length - 1);
        if (dueDateStr.isEmpty()) {
            throw Errors.MissingDeadlineDueDateError;
        }
        LocalDateTime dueDate = DateTime.getDateTimeFromUserInput(dueDateStr);

        String description = joinTokens(tokens, 1, indexOfDueDate - 1);
        if (description.isEmpty()) {
            throw Errors.MissingDeadlineDescriptionError;
        }

        Card taskAddedMessage = new Card(taskList.loadDeadline(description, dueDate));
        storage.saveTaskList(taskList);
        taskAddedMessage.print();
    }

    private void parseEvent(String input) throws InvalidBanterUsageError {
        String[] tokens = getTokens(input);

        int indexOfEnd = indexOf(tokens, EVENT_END);
        if (indexOfEnd == -1) {
            throw Errors.MissingEventEndError;
        }
        String endStr = joinTokens(tokens, indexOfEnd + 1, tokens.length - 1);
        if (endStr.isEmpty()) {
            throw Errors.MissingEventEndError;
        }
        LocalDateTime end = DateTime.getDateTimeFromUserInput(endStr);

        int indexOfStart = indexOf(tokens, EVENT_START);
        if (indexOfStart == -1) {
            throw Errors.MissingEventStartError;
        }
        String startStr = joinTokens(tokens, indexOfStart + 1, indexOfEnd - 1);
        if (startStr.isEmpty()) {
            throw Errors.MissingEventStartError;
        }
        LocalDateTime start = DateTime.getDateTimeFromUserInput(startStr);

        String description = joinTokens(tokens, 1, indexOfStart - 1);
        if (description.isEmpty()) {
            throw Errors.MissingEventDescriptionError;
        }

        Card taskAddedMessage = new Card(taskList.loadEvent(description, start, end));
        storage.saveTaskList(taskList);
        taskAddedMessage.print();
    }

    private void parseMark(String input) throws InvalidBanterUsageError {
        String[] tokens = getTokens(input);
        int taskNumber;
        try {
            taskNumber = getTaskNumber(tokens);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.InvalidMarkTaskNumberError;
        }
        Card taskDoneMessage = new Card(taskList.markTaskAsDone(taskNumber));
        storage.saveTaskList(taskList);
        taskDoneMessage.print();
    }

    private void parseUnmark(String input) throws InvalidBanterUsageError {
        String[] tokens = getTokens(input);
        int taskNumber;
        try {
            taskNumber = getTaskNumber(tokens);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.InvalidUnmarkTaskNumberError;
        }
        Card taskUndoneMessage = new Card(taskList.markTaskAsUndone(taskNumber));
        storage.saveTaskList(taskList);
        taskUndoneMessage.print();
    }

    private void parseDelete(String input) throws InvalidBanterUsageError {
        String[] tokens = getTokens(input);
        int taskNumber;
        try {
            taskNumber = getTaskNumber(tokens);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.InvalidDeleteTaskNumberError;
        }
        Card taskDeletedMessage = new Card(taskList.deleteTask(taskNumber));
        storage.saveTaskList(taskList);
        taskDeletedMessage.print();
    }

    /**
     * Finds tasks that contain the keyword(s) in the input.
     * @param input
     * @throws InvalidBanterUsageError
     */
    private void parseFind(String input) throws InvalidBanterUsageError {
        String[] tokens = getTokens(input);
        if (tokens.length == 1) {
            throw Errors.MissingKeywordError;
        }
        String keyword = joinTokens(tokens, 1, tokens.length - 1);
        Card taskFoundMessage = new Card(taskList.findTasks(keyword));
        taskFoundMessage.print();
    }


    // Helper methods
    private String[] getTokens(String input) {
        return input.split(SEPARATOR);
    }

    private String joinTokens(String[] tokens, int start, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (result.length() != 0) {
                result.append(SEPARATOR);
            }
            result.append(tokens[i]);
        }
        return result.toString();
    }

    private int indexOf(String[] tokens, String prefix) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(prefix)) {
                return i;
            }
        }
        return -1;
    }

    private int getTaskNumber(String[] tokens) throws IndexOutOfBoundsException, IllegalArgumentException {
        return Integer.parseInt(tokens[1]);
    }
}
