package command;
import duke.Ui;
import task.TaskList;

public abstract class Command {
    public void execute(TaskList tasks, Ui ui) throws Exception {
    }

    public boolean isExit() {
        return false;
    }
}
