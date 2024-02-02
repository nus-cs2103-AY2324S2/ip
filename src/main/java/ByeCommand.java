public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        ui.sendReply("Bye. Hope to see you again soon!");
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
