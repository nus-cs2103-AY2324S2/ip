package tyler.command;
import tyler.task.TaskList;
import tyler.task.Event;
import tyler.ui.Ui;
import tyler.storage.Storage;

import java.time.LocalDateTime;

/**
 * Represents an Event Command. An Event Command has two extra argument which is start
 * and end of the event.
 */
public class EventCommand extends AddCommand {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public EventCommand(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Execution of Event Command create a new Event Task with taskName, start and end.
     * Furthermore, added the task into the taskList. At last, this execution end with
     * an Ui, showed the line that the task is added.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(this.taskName, this.start, this.end);
        tasks.addTask(newTask);
        int num = tasks.getNumOfTasks();
        ui.showTaskAdded(newTask, num);
    }
}
