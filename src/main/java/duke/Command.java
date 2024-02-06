package duke;

public abstract class Command {
    private boolean isExit;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean b) {
        isExit = b;
    }
}
