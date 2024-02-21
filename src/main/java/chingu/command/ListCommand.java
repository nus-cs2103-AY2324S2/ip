package chingu.command;

import chingu.Storage;
import chingu.task.TaskList;
import chingu.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String Response = ui.announceListing();
        for (int i = 0; i < tasks.getSize(); i++) {
            int n = i + 1;
            Response = Response + n + ". " + tasks.getTask(i).toString() + "\n";
        }
        return Response;
    }

    public boolean isExit() {
        return false;
    }
}
