package doummi.command;

import doummi.Storage;
import doummi.task.TaskList;
import doummi.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.announceListing();
        String Response = "";
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
