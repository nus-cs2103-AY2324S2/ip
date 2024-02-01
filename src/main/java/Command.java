public abstract class Command {
    protected boolean isExit = false;
    protected boolean isExit() {
        return this.isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException;
}