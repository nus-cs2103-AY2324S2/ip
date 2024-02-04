package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

public abstract class Handler {

    public abstract void handle(TaskList tasks, Ui ui) throws EarlException;

    public static Handler dispatch(String[] command) {
        switch (command[0]) {
        case "list":
            return new listHandler();
        case "mark":
            return new markHandler(command);
        case "unmark":
            return new unmarkHandler(command);
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            // fallthrough
            return new taskHandler(command);
        case "delete":
            return new deleteHandler(command);
        default:
            return new unknownHandler();
        }
    }
}
