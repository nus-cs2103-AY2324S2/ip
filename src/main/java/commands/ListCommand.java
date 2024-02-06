package commands;

import exceptions.LeluException;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        System.out.println(tasks.toString());
    }
}
