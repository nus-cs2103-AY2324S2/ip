public class MarkCommand extends Command {
    private final int i;

    MarkCommand(int i) {
        this.i = i;
    }

    /**
     * Marks a task as completed based on the given index.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task updatedTask = taskList.markTask(i - 1);
        ui.displayMarkedTask(updatedTask);
    }
}
