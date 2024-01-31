package dibo;
public abstract class Command {

    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws DiboException;
    public boolean isBye() {
        return false;
    }

}
