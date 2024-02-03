public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String[] commands, int tasksSize) throws EggyException {
        if (commands.length < 2) {
            throw new IncompleteCommandException("unmark");
        }
        try {
            int taskNumber = Integer.parseInt(commands[1]);
            if (taskNumber < 1 || taskNumber > tasksSize) {
                throw new TaskListIndexOutOfBoundsException(taskNumber, tasksSize);
            }
            this.index = taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new TaskNumberFormatException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Task task = tasks.getTask(index);
        task.unmarkDone();
        ui.printTaskUnmarkedDone(task);
        storage.save(tasks);
    }
}
