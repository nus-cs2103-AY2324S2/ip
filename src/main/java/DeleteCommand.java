public class DeleteCommand extends Command{

    private int zeroItem;

    DeleteCommand(int oneItem) {
        this.zeroItem = oneItem - 1;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = zeroItem + 1;
        if (taskNumber < 1 || taskNumber > tasks.getSize() || tasks.get(taskNumber - 1) == null) {
            throw new DukeException("\nError! Task number '" + taskNumber + "' does not exist.\n");
        }
        Task description = tasks.get(zeroItem);
        tasks.deleteTask(zeroItem);
        ui.deleteResponse(description, tasks);
        storage.saveList(tasks.getTasks());
    }

}
