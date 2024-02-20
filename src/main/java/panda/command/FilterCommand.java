package panda.command;

import panda.component.*;

import panda.exception.PandaException;

public class FilterCommand extends Command {
    private String tag;

    /**
     * Constructs a new FindCommand.
     * 
     * @param fString the string to find in tasks.
     */
    public FilterCommand(String tag) {
        this.tag = tag;
    }

    /**
     * Does nothing, as the FindCommand does not need to perform any action on the TaskList alone.
     * 
     * @param tlist the TaskList on which the command is executed.
     */
    public void execute(TaskList tlist) {
        return;
    }

    /**
     * Finds tasks in the TaskList that contain the find string, saves changes to the cache file, and generate a reply.
     * 
     * @param tlist the TaskList on which the command is executed.
     * @param cacheFile the current storage. (unused)
     * @return the TaskList containing all matches
     * @throws PandaException if an error occurs during execution.
     */
    public String execute(TaskList tlist, Storage cacheFile) throws PandaException {
        assert tlist != null;
        TaskList filteredTasks = tlist.filter(tag);
        return filteredTasks.toString();
    }

    /**
     * Checks if the command is an exit command.
     * 
     * @return always false, as this command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if this command is equal to another object.
     * 
     * @param o the object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof FilterCommand)) {
            return false;
        }
         
        FilterCommand c = (FilterCommand) o;

        return tag.equals(c.tag);
    }
}
