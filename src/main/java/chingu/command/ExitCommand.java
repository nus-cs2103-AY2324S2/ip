package chingu.command;

import chingu.Storage;
import chingu.task.TaskList;
import chingu.Ui;

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
