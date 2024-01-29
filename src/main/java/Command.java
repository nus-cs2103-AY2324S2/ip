public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws WeiException;

    public abstract boolean isExit();

}
