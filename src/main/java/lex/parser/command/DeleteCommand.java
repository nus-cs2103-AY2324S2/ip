package lex.parser.command;

import lex.tasks.Task;
import lex.tasks.TaskList;
import lex.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param inputs The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     */
    public DeleteCommand(String[] inputs, TaskList tasks, Ui ui) {
        this.inputs = inputs;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        int index = Integer.parseInt(inputs[1]) - 1;
        Task removedTask = tasks.remove(index);

        ui.print("Noted. I've removed this task:");
        ui.print("  " + removedTask);
        ui.print("Now you have " + tasks.size() + " tasks in the list.");

        return false;
    }
}

