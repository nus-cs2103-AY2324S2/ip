package lrbg.codriver.command;

import lrbg.codriver.data.Event;
import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String description;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public EventCommand(String description, LocalDate fromDate, LocalDate toDate) {
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Event(this.description, this.fromDate, this.toDate);
        tasks.addTask(newTask);
        return ui.showAddTask(newTask, tasks.size());
    }

    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof EventCommand) {
            EventCommand other = (EventCommand) obj;
            return other.description.equals(this.description) && other.fromDate.equals(this.fromDate)
                    && other.toDate.equals(this.toDate);
        } else {
            return false;
        }
    }
}
