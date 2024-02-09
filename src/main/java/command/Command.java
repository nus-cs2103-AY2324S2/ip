package command;
import tasklist.TaskList;
import ui.Ui;

public abstract class Command {
    Boolean isExit;
    TaskList tasks;
    Ui ui;
    public abstract void execute();
    public void setTasksAndUi(TaskList tasks, Ui ui) {
        this.ui = ui;
        this.tasks = tasks;
    }
}
