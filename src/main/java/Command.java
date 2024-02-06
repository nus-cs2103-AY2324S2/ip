public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui);
    public boolean isExit() {
        return false;
    }
}
