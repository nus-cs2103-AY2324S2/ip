class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
