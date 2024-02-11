package Duke.command;
import Duke.*;
import Duke.task.*;
/**
 * Represent the command of deleting a task from the task list
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Construct a command to delete a task with a particular index from the task list
     *
     * @param index the index of the task that will be deleted
     */
    public DeleteCommand(int index){
        this.deleteIndex = index;
    }

    /**
     * Execute the command of deleting a task from the task list.
     *
     * @param tsks the List of tasks the command may work on
     * @param ui to manage the interaction with users
     * @param storage to store the product of execution locally.
     */
    @Override
    public void execute(TaskList tsks, Ui ui, Storage storage){
        String temp = tsks.delete(deleteIndex);
        Ui.print_message("Noted. I've removed this task:\n  "
                +temp+"\n "+
                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
        storage.writeDisk(tsks.accessList());
    }

    /**
     * Determine whether this command is an exit command
     *
     * @return false, because this is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}