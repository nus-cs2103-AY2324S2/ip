package command;
import tasklist.TaskList;
import ui.Ui;

public abstract class Command {
    Boolean isExit;
    abstract void execute(TaskList tasks, Ui ui);
}
