package commands;

import core.Ui;
import data.Storage;
import tasks.TaskList;

//CHECKSTYLE.OFF: MissingJavadocType
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(input, ui);
    }
}
