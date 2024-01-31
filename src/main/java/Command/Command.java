package Command;
import TaskList.TaskList;
import UiRelated.Ui;
public abstract class Command {
    public abstract void execute(TaskList t, Ui ui);
}
