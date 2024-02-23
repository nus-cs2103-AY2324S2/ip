package lex.parser.command;

import lex.tasks.TaskList;
import lex.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class UnmarkCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param inputs The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     */
    public UnmarkCommand(String[] inputs, TaskList tasks, Ui ui) {
        this.inputs = inputs;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        int index = Integer.parseInt(inputs[1]) - 1;
        tasks.get(index).setIsDone(false);

        ui.print("OK, I've marked this task as not done yet:");
        ui.print(tasks.get(index).toString());

        return false;
    }
}
