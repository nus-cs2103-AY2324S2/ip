package earl.logic;

import earl.exceptions.EarlException;
import earl.util.TaskList;
import earl.util.Ui;

/**
 * Abstract class representing a handler of a specific command.
 * <p>
 * ALl command handlers extend from this class. The dispatch method
 * should be updated to return any new command handlers.
 */
public abstract class Handler {

    /**
     * Executes an action based on the input command.
     * <p>
     * May modify tasks and interact with the user through the {@code TaskList}
     * and {@code Ui} arguments.
     *
     * @param tasks           a {@code TaskList} object
     * @param ui              a {@code Ui} object
     * @throws EarlException  if the user's command is incomprehensible
     */
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
