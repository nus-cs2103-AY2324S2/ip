public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.get(index - 1);
            t.unmark();
            setExit(false);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}