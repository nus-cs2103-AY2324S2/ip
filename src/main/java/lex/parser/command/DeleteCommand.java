package lex.parser.command;

import lex.tasks.TaskList;
import lex.tasks.Task;
import lex.ui.Ui;

public class DeleteCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

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
        ui.print("Now you have " + tasks.size() + " lex.tasks in the list.");

        return false;
    }
}

