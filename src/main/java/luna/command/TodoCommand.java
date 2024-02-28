package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.Ui;
import luna.entry.ListEntryTodo;

/**
 * Represents a to do command which adds a to-do task to the task list
 */
public class TodoCommand extends Command {

    private String taskName;


    /**
     * Construct a Command to have to-do command type and assign its task name
     *
     * @param tn task name
     */
    public TodoCommand(String tn) {
        super(CommandType.TODO);
        taskName = tn;
    }

    /**
     * Attach the reference tasklist, ui and storage to execute the command on.
     * Adds a new to do task to the tasklist
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.add(new ListEntryTodo(taskName, false));
        ui.shiftedPrint("Added To-do task");
    }

}
