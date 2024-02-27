package panda.command;

import panda.component.*;
import panda.task.Task;

import panda.exception.PandaException;

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
        assert tlist != null;
        tlist.insert(task);
    }

    /**
     * Inserts the task into the given TaskList, saves changes to the cache file, and generate a reply.
     * 
     * @param tlist the TaskList into which the task is inserted.
     * @param cacheFile the cache file to save changes to.
     * @return the reply generated after insertion
     * @throws PandaException if an error occurs during execution.
     */
    public String execute(TaskList tlist, Storage cacheFile) throws PandaException {
        assert tlist != null;
        tlist.insert(task);
        cacheFile.save(tlist);
        return "Got it. I've added this task:" 
            + "\n " + tlist.getTaskString(tlist.size()) 
            + "\nNow you have " + tlist.size() + " tasks in the list.";
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
