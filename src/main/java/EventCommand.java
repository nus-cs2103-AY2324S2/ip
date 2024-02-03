public class EventCommand extends Command {

    private Event event;

    public EventCommand(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(event);
        ui.showAddMsg(event, tasks.getTaskSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
