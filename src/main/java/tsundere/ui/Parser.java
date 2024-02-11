package tsundere.ui;

import tsundere.exception.GeneralException;
import tsundere.task.TaskList;

/**
 * Encapsulates a Parser object that parses user input and carries out the respective action.
 */
public class Parser {

    public static String name;
    public Parser(String name) {
        Parser.name = name;
    }

    /**
     * Parses command given and executes corresponding action.
     *
     * @return response to given input.
     * @throws GeneralException If actions executed cause exceptions.
     */
    public String execute() throws GeneralException {

        if (name.contains("unmark")) {
            return TaskList.unmarkTask();
        } else if (name.contains("mark")) {
            return TaskList.markTask();
        } else if (name.contains("delete")) {
            return TaskList.deleteTask();
        } else if (name.contains("list")) {
            return TaskList.listTasks();
        } else if (name.contains("deadline")) {
            return TaskList.addDeadlineTask();
        } else if (name.contains("event")) {
            return TaskList.addEventTask();
        } else if (name.contains("todo")) {
            return TaskList.addToDoTask();
        } else if (name.contains("find")) {
            return TaskList.findTasks();
        } else if (name.contains("untag")) {
            return TaskList.untagTask();
        } else if (name.contains("tag")) {
            return TaskList.tagTask();
        } else {
            return ("Don't talk to me!\nGive me proper instructions!");
        }

    }
}
