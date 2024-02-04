public class UnmarkCommand extends Command {
    private final int i;

    UnmarkCommand(int i) {
        this.i = i;
    }

    /**
     * Unmarks a task as completed based on the given index.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task updatedTask = taskList.unmarkTask(i - 1);
        ui.displayUnmarkedTask(updatedTask);
    }
}
