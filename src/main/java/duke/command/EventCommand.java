package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

public class EventCommand extends Command {

    private String description;
    private String startTime;
    private String endTime;

    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event;
        event = new Event(description, startTime, endTime);
        tasks.add(event);
        ui.taskResponse(event, tasks);
        storage.saveList(tasks.getTasks());
    }

}
