package arona;

public abstract class Command {
    protected String fullCommand;
    protected boolean exit;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws FileException, TaskException;
    public boolean isExit() {
        return this.exit;
    }
}
