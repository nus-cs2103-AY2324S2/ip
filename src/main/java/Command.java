public abstract class Command {
    protected boolean isDone;
    public Command(boolean isDone) {
        this.isDone = isDone;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
    public boolean isExit() {
        return this.isDone;
    }

}
