package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

public abstract class Handler {

    public abstract void handle(TaskList tasks, Ui ui) throws EarlException;

    public static Handler dispatch(String[] command) {
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
        default:
            return new UnknownHandler();
        }
    }
}
