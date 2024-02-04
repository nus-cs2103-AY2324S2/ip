/**
 * The AddEventCommand class is used to encapsulate an add event task
 * command, which is executed upon invoking the execute() method.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.EventTask;
import bond.task.Task;
import bond.task.TaskList;

public class AddEventCommand extends AddCommand {

    private String start;
    private String end;

    public AddEventCommand(String taskName, String start, String end) {
        super("event", taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task newTask = new EventTask(super.taskName, this.start, this.end);
        tasks.addTask(newTask);
        ui.taskAdded(newTask, tasks);
        storage.storeTask(newTask, tasks);
    }

}
