package commands;

import exceptions.LeluException;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        int i = Integer.parseInt(message.split(" ")[1]) - 1;
        tasks.markTask(i);
    }

}
