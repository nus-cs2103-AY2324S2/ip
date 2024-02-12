package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {

    String taskDesc;
    LocalDate fromDate;
    String fromTime;
    LocalDate toDate;
    String toTime;

    public EventCommand(String input, String taskDesc, LocalDate fromDate, String fromTime, LocalDate toDate, String toTime) {
        super(input);
        this.taskDesc = taskDesc;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();

        Task t = new Event(taskDesc, fromDate, fromTime, toDate, toTime);
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
