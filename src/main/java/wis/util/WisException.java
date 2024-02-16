package wis.util;

import java.io.IOException;

import wis.Action;

/**
 * Contains exception handlers specific to this application.
 */
public class WisException {
    /**
     * Handles exceptions thrown by actions that fail to execute.
     */
    public static String handleActionException(Action action) {
        StringBuilder sb = new StringBuilder();
        switch (action) {
        case ADD_TODO:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Input format unsupported."));
            sb.append(Printer.println("Use this format: todo <task>"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        case ADD_DEADLINE:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Input format unsupported."));
            sb.append(Printer.println("Use this format: deadline <task> /by <time>"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        case ADD_EVENT:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Input format unsupported."));
            sb.append(Printer.println("Use this format: event <task> /from <time> /to <time>"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        case MARK:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Please enter a valid index."));
            sb.append(Printer.println("Use this format: mark <task_index>"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        case UNMARK:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Please enter a valid index."));
            sb.append(Printer.println("Use this format: unmark <task_index>"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        case DELETE:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Please enter a valid index."));
            sb.append(Printer.println("Use this format: delete <task_index>"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        case FIND:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Use this format: find <keyword>"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        case INVALID:
            sb.append(Printer.DECORATOR);
            sb.append(Printer.println("Input format unsupported."));
            sb.append(Printer.println("Please begin your input with one of the following keywords:"));
            sb.append(Printer.println("todo, deadline, event, list, find, mark, unmark, delete, undo"));
            sb.append(Printer.DECORATOR);
            return sb.toString();
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    /**
     * Handles exceptions due to failure to save file.
     */
    public static String handleSaveFileException(IOException e) {
        StringBuilder sb = new StringBuilder();
        sb.append(Printer.DECORATOR);
        sb.append(Printer.println("Failed to save file to local."));
        sb.append(Printer.println(e.getMessage()));
        sb.append(Printer.DECORATOR);
        return sb.toString();
    }

    /**
     * Handles exceptions due to failure to load file.
     */
    public static String handleLoadFileException() {
        StringBuilder sb = new StringBuilder();
        sb.append(Printer.DECORATOR);
        sb.append(Printer.println("Failed to load tasks from local."));
        sb.append(Printer.println("Local data file might be corrupted."));
        sb.append(Printer.DECORATOR);
        return sb.toString();
    }

    /**
     * Handles exceptions due to wrong format of date and time.
     */
    public static String handleDateTimeException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(Printer.DECORATOR);
        sb.append(Printer.println(e.getMessage()));
        sb.append(Printer.println("Please use this format for time:"));
        sb.append(Printer.println("yyyy-mm-dd hh:mm"));
        sb.append(Printer.println("For example: 2024-03-03 23:59"));
        sb.append(Printer.DECORATOR);
        return sb.toString();
    }
}
