public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws ExceptionDuke;
    public abstract boolean isExit();
}
