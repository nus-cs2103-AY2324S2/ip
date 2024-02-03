public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index <= tasks.getTaskSize() && this.index > 0) {
            tasks.mark(this.index);
            ui.showMarkMsg(tasks.getTasks().get(this.index - 1));
            storage.save(tasks);
        } else {
            throw new DukeException("Invalid index." +
                    "Please provide a valid index within the range 1 to " + tasks.getTaskSize() + ".");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
