package asher.Commands;

import asher.Ui.Ui;
import asher.Tasks.TaskList;


public abstract class Command {
    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract String execute(Ui ui, TaskList taskList, Storage storage);
}
