package bob;

public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws BobException;

    public boolean isExit() {
        return false;
    }
}
