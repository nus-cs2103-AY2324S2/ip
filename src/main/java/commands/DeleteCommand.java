package commands;

import core.Ui;
import data.Storage;
import tasks.TaskList;

//CHECKSTYLE.OFF: MissingJavadocType
public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(input, ui, storage);
    }
}
