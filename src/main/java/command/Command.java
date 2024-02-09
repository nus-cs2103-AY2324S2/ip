package command;
import tasklist.TaskList;
import ui.Ui;

public abstract class Command {
    Boolean isBye = false;
    TaskList tasks;
    Ui ui;
    public abstract void execute();
    public void setTasksAndUi(TaskList tasks, Ui ui) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public Boolean getIsBye() {
        return isBye;
    }
}
