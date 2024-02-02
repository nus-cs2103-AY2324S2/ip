package lex.parser.command;

import lex.tasks.TaskList;
import lex.ui.Ui;

public class FindCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

    public FindCommand(String[] inputs, TaskList tasks, Ui ui) {
        this.inputs = inputs;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws Exception {
        var find = inputs[1];
        ui.print("Here are the matching tasks in your list:");

        for (var task : tasks) {
            if (task.getTitle().contains(find)) {
                ui.print(String.valueOf(task));
            }
        }

        return false;
    }
}
