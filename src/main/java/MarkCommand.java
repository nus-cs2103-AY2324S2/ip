public class MarkCommand extends Command {
    private int taskNumber;
    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.markTask(this.taskNumber);
        ui.printMarkTask(currTask);
    }
}
