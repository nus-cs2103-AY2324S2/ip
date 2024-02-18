package commands;

import core.Ui;
import data.Storage;
import tasks.TaskList;

//CHECKSTYLE.OFF: MissingJavadocType
public class TagCommand extends Command {
    private String input;

    public TagCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.tag(input, ui, storage);
    }
}
