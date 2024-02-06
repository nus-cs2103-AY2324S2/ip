package duke;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.delete(this.index);
            setExit(false);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
