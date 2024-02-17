package jerry.command;

import jerry.TaskList;
import jerry.Ui;

public abstract class Command {

    protected Ui ui;
    protected TaskList tasks;

    public Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public abstract String execute();
}
