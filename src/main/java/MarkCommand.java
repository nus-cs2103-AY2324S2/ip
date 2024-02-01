public class MarkCommand extends Command {
    int taskNumber;
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String t = tasks.mark(this.taskNumber);
        ui.showResult("Nice, I've marked this task as done:");
        ui.showResult(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
