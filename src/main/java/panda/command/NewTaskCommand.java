package panda.command;
import panda.component.*;
import panda.task.Task;
public class NewTaskCommand extends Command {
    private Task task;

    /**
     * Constructs a new NewTaskCommand.
     * 
     * @param task the task to be inserted.
     */
    public NewTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Inserts the task into the given TaskList.
     * 
     * @param tlist the TaskList into which the task is inserted.
     */
    public void execute(TaskList tlist) {
        tlist.insert(task);
    }

    /**
     * Inserts the task into the given TaskList, updates the UI, and saves changes to the cache file.
     * 
     * @param tlist the TaskList into which the task is inserted.
     * @param ui the UI to update after insertion.
     * @param cacheFile the cache file to save changes to.
     */
    public void execute(TaskList tlist, Ui ui, Storage cacheFile) {
        tlist.insert(task);
        cacheFile.save(tlist);
        ui.showReply("Got it. I've added this task:\n " + tlist.taskString(tlist.size() - 1) + "\nNow you have " + tlist.size() + " tasks in the list.");
    }

    /**
     * Checks if the command is an exit command.
     * 
     * @return always false, as this command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof NewTaskCommand)) {
            return false;
        }
         
        NewTaskCommand c = (NewTaskCommand) o;

        return task.equals(c.task);
    }
}
