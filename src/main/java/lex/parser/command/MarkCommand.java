package lex.parser.command;

import lex.TaskList;
import lex.ui.Ui;

public class MarkCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

    public MarkCommand(String[] inputs, TaskList tasks, Ui ui) {
        this.inputs = inputs;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        int index = Integer.parseInt(inputs[1]) - 1;
        tasks.get(index).setIsDone(true);


        ui.print("Nice! I've marked this task as done:");
        ui.print(tasks.get(index).toString());

        return false;
    }
}
