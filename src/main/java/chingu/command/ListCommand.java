package chingu.command;

import chingu.Storage;
import chingu.task.TaskList;
import chingu.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String Response = ui.announceListing();
        for (int i = 0; i < tasks.getSizeNumber(); i++) {
            int n = i + 1;
            Response = Response + n + ". " + tasks.getTask(i).toString() + "\n";
        }
        return Response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
