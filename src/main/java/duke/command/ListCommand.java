package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.announceListing();
        for (int i = 0; i < tasks.getSize(); i++) {
            int n = i + 1;
            System.out.println(n + ". " + tasks.getTask(i).toString());
        }
    }

    public boolean isExit() {
        return false;
    }
}
