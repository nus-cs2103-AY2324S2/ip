public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(deadline);
        ui.showAddMsg(deadline, tasks.getTaskSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
