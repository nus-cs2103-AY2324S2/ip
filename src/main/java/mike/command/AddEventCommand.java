package mike.command;

import mike.MikeException;
import mike.TaskList;
import mike.task.Event;
import mike.task.Task;

public class AddEventCommand extends AddCommand {
    private final String startDate;
    private final String endDate;

    public AddEventCommand(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(TaskList taskList) throws MikeException {
        Task newTask = new Event(description, startDate, endDate);
        taskList.add(newTask);
        respond(taskList, newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
