package doummi.command;

import doummi.Storage;
import doummi.task.TaskList;
import doummi.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return ui.sayGoodBye();
    }

    public boolean isExit() {
        return true;
    }
}
