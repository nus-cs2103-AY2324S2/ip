public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        tasks.getTask(index).markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n" + tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
