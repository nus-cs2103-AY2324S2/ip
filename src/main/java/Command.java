public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws LivException;
    public boolean isExit() {
        return false;
    }

    public boolean changedData() {
        return false;
    }
}
