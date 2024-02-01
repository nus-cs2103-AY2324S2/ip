package lex.parser.command;

import lex.TaskList;
import lex.ui.Ui;

public class ListCommand implements Command {
    private final TaskList tasks;
    private final Ui ui;

    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        for (int i = 0; i < tasks.size(); i++) {
            ui.print((i + 1) + ". " + tasks.get(i));
        }

        return false;
    }
}
