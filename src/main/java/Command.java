public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }
    /**
     * Executes different type of command.
     *
     * @param taskList TaskList to be operated.
     * @param ui Ui that handles input and output.
     * @param storage Storage that handles operation with hard disk.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
