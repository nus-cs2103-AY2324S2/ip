public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException;

    public abstract boolean isExit();
}
