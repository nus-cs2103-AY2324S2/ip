public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String[] commands, int tasksSize) throws EggyException {
        if (commands.length < 2) {
            throw new IncompleteCommandException("delete");
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
        Task task = tasks.removeTask(index);
        ui.printTaskRemoved(task, tasks.getSize());
        storage.save(tasks);
    }
}
