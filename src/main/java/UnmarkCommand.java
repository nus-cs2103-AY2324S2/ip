public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        tasks.getTask(index).markAsNotDone();
        ui.showMessage("Nice! I've marked this task as undone:\n" + tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
