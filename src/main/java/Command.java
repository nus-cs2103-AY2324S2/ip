abstract class Command {
    protected final String command;

    Command(String command) {
        this.command = command;
    }
    abstract void execute(TaskList tasks, Ui ui, Storage storage);

    boolean isExit() {
        return false;
    }
}
