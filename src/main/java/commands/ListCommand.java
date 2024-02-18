package commands;

import core.Ui;
import data.Storage;
import tasks.TaskList;

//CHECKSTYLE.OFF: MissingJavadocType
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}
