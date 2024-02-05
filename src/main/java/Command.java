abstract class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }
    public boolean isExit() {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }

}
