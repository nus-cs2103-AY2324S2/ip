public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, UI ui) {
        tasks.addTask(event);
        UI.displayEventCommand(event);
    }
}
