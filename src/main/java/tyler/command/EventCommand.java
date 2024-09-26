package tyler.command;

import java.time.LocalDateTime;

import tyler.storage.Storage;
import tyler.task.Event;
import tyler.task.TaskList;
import tyler.ui.Ui;

/**
 * Represents an Event Command. An Event Command has two extra argument which is start
 * and end of the event.
 */
public class EventCommand extends AddCommand {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for Event Command
     *
     * @param name  Name of Task
     * @param start Event start date
     * @param end   Event end date
     */
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(this.taskName, this.start, this.end);
        assert newTask != null;
        tasks.addTask(newTask);
        int num = tasks.getNumOfTasks();
        return ui.showTaskAdded(newTask, num);
    }
}
