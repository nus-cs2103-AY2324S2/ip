public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.sendReply(tasks.list());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
