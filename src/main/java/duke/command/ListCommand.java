package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

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
