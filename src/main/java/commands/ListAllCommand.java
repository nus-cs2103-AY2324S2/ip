package commands;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;

public class ListAllCommand extends Command{
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        new ListCommand(tasks).execute(tasks, ui, storage);
    }
}
