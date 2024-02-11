package Duke.command;
import Duke.*;
import Duke.task.*;

/**
 * Represent the command of adding a task to the task list
 */
public class AddCommand extends Command {
    private task tsk;

    /**
     * Construct a command to add task into the task list
     *
     * @param tsk the task to be added
     */
    public AddCommand(task tsk){
        this.tsk = tsk;
    }

    /**
     * Execute the command of adding a task to the task list.
     *
     * @param tsks the List of tasks the task will be added to
     * @param ui show the deleting result to the user
     * @param storage store the product of execution locally.
     */
    @Override
    public void execute(TaskList tsks, Ui ui, Storage storage){
        tsks.insert(this.tsk);
        Ui.print_message("Got it. I've added this task:\n  "
                +tsk.toString()+"\n "+
                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
        storage.writeDisk(tsks.accessList());
    }

    /**
     * Determine whether this command is an exit command
     *
     * @return false, because this is not an exit command
     */
    @Override
    public boolean isExit(){
        return false;
    }
}
