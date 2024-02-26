package gandalf.commands;

import gandalf.Storage;
import gandalf.TaskList;
import gandalf.Ui;

public class ListCommand extends Command {

    public ListCommand(String commandName, TaskList tasks, Storage storage, Ui ui) {
        super(commandName, tasks, storage, ui);
    }

    @Override
    public void execute() {
        tasks.list();
        ui.listUi(this.tasks);
    }
}
