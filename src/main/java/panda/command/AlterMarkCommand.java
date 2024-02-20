package panda.command;

import panda.component.*;

import panda.exception.PandaException;
import panda.exception.OutOfBoundsException;

public class AlterMarkCommand extends Command {
    private int idx;
    private boolean isMarked;

    /**
     * Constructs a new AlterMarkCommand.
     * 
     * @param idx the index of the task to alter mark.
     * @param isMarked the logic to mark the task
     */
    public AlterMarkCommand(int idx, boolean isMarked) {
        this.idx = idx;
        this.isMarked = isMarked;
    }

    /**
     * Executes the command on the given TaskList.
     * 
     * @param tlist the TaskList on which the command is executed.
     * @throws PandaException if an error occurs during execution.
     */
    public void execute(TaskList tlist) throws PandaException {
        assert tlist != null;
        if(idx - 1 >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        if(isMarked) {
            tlist.mark(idx);
        } else {
            tlist.unmark(idx);
        }
    }

    /**
     * Executes the command on the given TaskList, saves changes to the cache file, and generate a reply.
     * 
     * @param tlist the TaskList on which the command is executed.
     * @param cacheFile the cache file to save changes to.
     * @return the reply generated from running the command
     * @throws PandaException if an error occurs during execution.
     */
    public String execute(TaskList tlist, Storage cacheFile) throws PandaException {
        assert tlist != null;
        if(idx - 1 >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        if(isMarked) {
            tlist.mark(idx);
        } else {
            tlist.unmark(idx);
        }
        cacheFile.save(tlist);
        return "Nice! I've marked this task as done:\n  " + tlist.getTaskString(idx);
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

        if (!(o instanceof AlterMarkCommand)) {
            return false;
        }
         
        AlterMarkCommand c = (AlterMarkCommand) o;

        return idx == c.idx && isMarked == c.isMarked;
    }
}
