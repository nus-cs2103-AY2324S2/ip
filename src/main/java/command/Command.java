package command;
import tasklist.TaskList;
import ui.Ui;
import msg.Msg;

public abstract class Command {
    Boolean isBye = false;
    TaskList tasks;
    Ui ui;
    String response = "";
    public abstract String execute();
    public void setTasksAndUi(TaskList tasks, Ui ui) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public Boolean getIsBye() {
        return isBye;
    }
}
