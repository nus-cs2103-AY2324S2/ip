package Duke.command;
import Duke.*;
import Duke.task.*;
public abstract class Command{
    public abstract void execute(TaskList tsk, Ui ui, Storage storage);
    public abstract boolean isExit();

}