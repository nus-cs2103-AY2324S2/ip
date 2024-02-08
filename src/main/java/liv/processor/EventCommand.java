package liv.processor;

import liv.task.Event;
import liv.container.TaskList;
import liv.ui.Ui;

public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(event);
        Ui.displayEventCommand(event);
    }

    @Override
    public boolean hasChangedData() {
        return true;
    }
}
