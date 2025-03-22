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
        return "Commands:\ntd [DESCRIPTION]: add a todo"
                + "\ndl [DESCRIPTION] /by DD/MM/YYYY HHMM: add a deadline"
                + "\ne [DESCRIPTION] /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM: add an event"
                + "\nrm [INDEX]: remove a task"
                + "\nfind [KEYWORDS]: search for tasks"
                + "\nls: list all tasks"
                + "\nsort: sort all tasks by chronological order"
                + "\nmk [INDEX]: mark a task as done"
                + "\num [INDEX]: unmark a task"
                + "\nbb: exit Naruto";
    }
}
