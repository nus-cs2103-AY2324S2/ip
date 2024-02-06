package command;
import duke.Ui;
import task.Event;
import task.TaskList;

public class AddEventCommand extends Command {
    private String description;
    private String start;
    private String end;

    public AddEventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Event event = new Event(description, start, end);
        tasks.add(event);
        ui.showAddTask(event, tasks.size());
    }
    
}
