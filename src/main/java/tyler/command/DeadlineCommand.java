package tyler.command;

import java.time.LocalDateTime;

import tyler.storage.Storage;
import tyler.task.Deadline;
import tyler.task.TaskList;
import tyler.ui.Ui;

/**
 * Represent a Deadline command. A Deadline command has an extra argument which is
 * end to store in this command.
 */
public class DeadlineCommand extends AddCommand {
    protected LocalDateTime end;

    /**
     * Constructor for DeadlineCommand
     *
     * @param name Name of the Deadline Task
     * @param end  Deadline of the Task
     */
    public DeadlineCommand(String name, LocalDateTime end) {
        super(name);
        this.end = end;
    }

    /**
     * Execution of Deadline command create a new Deadline Task with taskName
     * and end. Furthermore, added the task created into the taskList. At last, this
     * execution end with an Ui, showed the line that the task is added.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(this.taskName, this.end);
        assert newTask != null;
        tasks.addTask(newTask);
        int num = tasks.getNumOfTasks();
        return ui.showTaskAdded(newTask, num);
    }
}
