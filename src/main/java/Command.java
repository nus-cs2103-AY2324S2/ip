public abstract class Command {
    private boolean isExit;

    Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    protected void setIsExit() {
        this.isExit = true;
    }

    abstract void execute(Ui ui, TaskList taskList) throws InvalidInputFormatException;
}
