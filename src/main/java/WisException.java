import java.io.IOException;
import java.util.InputMismatchException;

public class WisException {
    public static void ActionExceptionHandler(Action action) {
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

    public static void SaveFileExceptionHandler(IOException e) {
        Printer.printDecorator();
        Printer.println("Failed to save file to local.");
        Printer.println(e.getMessage());
        Printer.printDecorator();
    }

    public static void LoadFileExceptionHandler() {
        Printer.printDecorator();
        Printer.println("Failed to load tasks from local.");
        Printer.println("Local data file might be corrupted.");
        Printer.printDecorator();
    }
}
