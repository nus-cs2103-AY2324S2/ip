public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws LivException {
        int trueIndex = index - 1;
        Task removed = tasks.deleteTask(trueIndex);
        Ui.displayDeleteCommand(removed);
    }
}
