package mike.command;

import mike.MikeException;
import mike.TaskList;
import mike.Ui;
import mike.command.Command;

public class ListCommand extends Command {
    public ListCommand() {
        super("" );
    }

    @Override
    public void execute(TaskList taskList) throws MikeException {
        if (taskList.isEmpty()) {
            throw new MikeException("You have no more tasks Sulley...");
        }
        Ui.display("You and I are a team.\nHere is the task list:");
        Ui.display(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
