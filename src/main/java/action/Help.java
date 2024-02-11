package action;

import java.io.IOException;

/**
 * Represents an action to display the user guide.
 * Implements the Action interface.
 */
public class Help implements Action {
    /**
     * Constructs a Help object.
     */
    public Help() {
    }
    /**
     * Executes the Help action by printing a guide.
     *
     * @return A string representing the result of the execution.
     */
    @Override
    public String execute() throws IOException {
        return "Commands:\ntd: add a todo\nrm <index>: remove a todo\nfind <keywords>: search for"
                + " tasks\nls: list all tasks\nsort: sort all tasks by "
                + "chronological order\nmk <index>: mark a task as done\num <index>: unmark a task\n"
                + "bb: exit Naruto\n";
    }
}
