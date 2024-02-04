package commands;

import core.Ui;
import data.Storage;
import tasks.TaskList;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommandFormats();
    }
}