public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList();
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
