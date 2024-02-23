package lex.parser.command;

import lex.tasks.TaskList;
import lex.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class ListCommand implements Command {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the ListCommand class.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     */
    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        if (tasks.isEmpty()) {
            ui.print("No tasks found.");
        }

        for (int i = 0; i < tasks.size(); i++) {
            ui.print((i + 1) + ". " + tasks.get(i));
        }

        return false;
    }
}
