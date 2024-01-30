public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage s);

    public abstract boolean isExit();
}

