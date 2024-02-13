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
    public static void actionExceptionHandler(Action action) {
        switch (action) {
        case ADD_TODO:
            Printer.printDecorator();
            Printer.println("Input format unsupported.");
            Printer.println("Use this format: todo <task>");
            Printer.printDecorator();
            break;
        case ADD_DEADLINE:
            Printer.printDecorator();
            Printer.println("Input format unsupported.");
            Printer.println("Use this format: deadline <task> /by <time>");
            Printer.printDecorator();
            break;
        case ADD_EVENT:
            Printer.printDecorator();
            Printer.println("Input format unsupported.");
            Printer.println("Use this format: event <task> /from <time> /to <time>");
            Printer.printDecorator();
            break;
        case MARK:
            Printer.printDecorator();
            Printer.println("Please enter a valid index.");
            Printer.println("Use this format: mark <task_index>");
            Printer.printDecorator();
            break;
        case UNMARK:
            Printer.printDecorator();
            Printer.println("Please enter a valid index.");
            Printer.println("Use this format: unmark <task_index>");
            Printer.printDecorator();
            break;
        case DELETE:
            Printer.printDecorator();
            Printer.println("Please enter a valid index.");
            Printer.println("Use this format: delete <task_index>");
            Printer.printDecorator();
            break;
        case FIND:
            Printer.printDecorator();
            Printer.println("Use this format: find <keyword>");
            Printer.printDecorator();
            break;
        case INVALID:
            Printer.printDecorator();
            Printer.println("Input format unsupported.");
            Printer.println("Please begin your input with one of the following keywords:");
            Printer.println("todo, deadline, event, list, mark, unmark, bye");
            Printer.printDecorator();
            break;
        default:
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    /**
     * Handles exceptions due to failure to save file.
     */
    public static void saveFileExceptionHandler(IOException e) {
        Printer.printDecorator();
        Printer.println("Failed to save file to local.");
        Printer.println(e.getMessage());
        Printer.printDecorator();
    }

    /**
     * Handles exceptions due to failure to load file.
     */
    public static void loadFileExceptionHandler() {
        Printer.printDecorator();
        Printer.println("Failed to load tasks from local.");
        Printer.println("Local data file might be corrupted.");
        Printer.printDecorator();
    }

    /**
     * Handles exceptions due to wrong format of date and time.
     */
    public static void dateTimeExceptionHandler(Exception e) {
        Printer.printDecorator();
        Printer.println(e.getMessage());
        Printer.println("Please use this format for time:");
        Printer.println("yyyy-mm-dd hh:mm");
        Printer.println("For example: 2024-03-03 23:59");
        Printer.printDecorator();
    }
}
