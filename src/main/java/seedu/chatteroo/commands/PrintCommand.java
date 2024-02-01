package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

public class PrintCommand extends Command {
    public PrintCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int listCount = tasks.getTaskListSize();
        if (listCount == 0) {
            ui.showNoTaskText();
        } else {
            tasks.printTasks(listCount);
        }
    }
}
