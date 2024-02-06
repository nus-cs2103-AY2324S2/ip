package earl.logic;

public class HandlerFactory {

    /**
     * Factory method to create relevant {@code Handler} object.
     *
     * @param command  the user input command
     * @return         a subclass of {@code Handler} that handles
     *                 the given command
     */
    public static Handler of(String[] command) {
        switch (command[0]) {
        case "list":
            return new ListHandler();
        case "mark":
            return new MarkHandler(command);
        case "unmark":
            return new UnmarkHandler(command);
        case "todo":
            return new TodoHandler(command);
        case "deadline":
            return new DeadlineHandler(command);
        case "event":
            return new EventHandler(command);
        case "delete":
            return new DeleteHandler(command);
        case "find":
            return new FindHandler(command);
        default:
            return new UnknownHandler();
        }
    }
}
