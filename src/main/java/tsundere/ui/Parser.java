package tsundere.ui;

import tsundere.exception.GeneralException;
import tsundere.task.TaskList;

public class Parser {

    public static String name;
    public Parser(String name) {
        Parser.name = name;
    }

    /**
     * Parses command given and executes corresponding action.
     *
     * @throws GeneralException If actions executed cause exceptions.
     */
    public String execute() throws GeneralException {

        if (name.contains("unmark")) {
            return TaskList.unmark();
        } else if (name.contains("mark")) {
            return TaskList.mark();
        } else if (name.contains("delete")) {
            return TaskList.delete();
        } else if (name.contains("list")) {
            return TaskList.list();
        } else if (name.contains("deadline")) {
            return TaskList.addDeadline();
        } else if (name.contains("event")) {
            return TaskList.addEvent();
        } else if (name.contains("todo")) {
            return TaskList.addToDo();
        } else if (name.contains("find")) {
            return TaskList.find();
        } else if (name.contains("untag")) {
            return TaskList.untag();
        } else if (name.contains("tag")) {
            return TaskList.tag();
        } else {
            return ("Don't talk to me!\nGive me proper instructions!");
        }

    }
}
