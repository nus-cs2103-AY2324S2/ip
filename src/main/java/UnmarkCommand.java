public class UnmarkCommand extends Command {
    int taskNumber;
    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task currTask = taskList.unmarkTask(this.taskNumber);
        ui.printUnmarkTask(currTask);
    }

}
