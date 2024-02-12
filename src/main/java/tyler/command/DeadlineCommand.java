package tyler.command;

import tyler.task.Deadline;
import tyler.ui.Ui;
import tyler.task.TaskList;
import tyler.storage.Storage;

import java.time.LocalDateTime;

/**
 * Represent a Deadline command. A Deadline command has an extra argument which is
 * end to store in this command.
 */
public class DeadlineCommand extends AddCommand {
    protected LocalDateTime end;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(this.taskName, this.end);
        tasks.addTask(newTask);
        int num = tasks.getNumOfTasks();
        ui.showTaskAdded(newTask, num);
    }
}
