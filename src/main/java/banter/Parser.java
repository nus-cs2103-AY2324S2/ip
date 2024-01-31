package banter;

import banter.enums.CommandType;
import banter.errors.InvalidBanterUsageError;
import banter.ui.Card;
import banter.tasks.TaskList;

import java.time.LocalDateTime;
import java.util.Scanner;
import banter.errors.Errors;
import banter.ui.Ui;
import banter.utilities.DateTime;

public class Parser {
    private final TaskList taskList;
    private final Storage storage;

    // Constants
    private static final String SEPARATOR = " ";
    private static final String DEADLINE_DUE_DATE = "/by";
    private static final String EVENT_START = "/from";
    private static final String EVENT_END = "/to";

    public Parser(Storage storage) {
        this.storage = storage;
        taskList = this.storage.loadTaskList();
    }

    // Methods
    public void printGreetMessage() {
        System.out.println(Ui.BANTER_LOGO);
        Ui.GREET_MESSAGE.print();
    }

    private void printExitMessage() {
        Ui.EXIT_MESSAGE.print();
    }

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
        Card taskAddedMessage = new Card(taskList.addTodo(description));
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

        Card taskAddedMessage = new Card(taskList.addDeadline(description, dueDate));
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

        Card taskAddedMessage = new Card(taskList.addEvent(description, start, end));
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
