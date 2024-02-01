package command;
import duke.Ui;
import duke.TaskList;

public abstract class Command {

    private TaskList taskList;
    private Ui ui;

    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws Exception;

    public abstract boolean isExit();
}
