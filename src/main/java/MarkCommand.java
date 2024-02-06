public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws LivException {
        int trueIndex = index - 1;
        Task task = TaskList.getTask(trueIndex);
        boolean currentState = task.getStatus();
        if (currentState) {
            throw new LivException("Mission already marked!");
        }
        task.markAsDone();
        Ui.displayMarkCommand(task);
    }
}
