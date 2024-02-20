package panda.command;

import panda.component.*;

import panda.exception.PandaException;
import panda.exception.OutOfBoundsException;

public class ModifyTagCommand extends Command {
    private int idx;
    private String tag;
    private boolean isTagged;

    /**
     * Constructs a new AlterMarkCommand.
     * 
     * @param idx the index of the task to alter mark.
     * @param isMarked the logic to mark the task
     */
    public ModifyTagCommand(int idx, String tag, boolean isTagged) {
        this.idx = idx;
        this.tag = tag;
        this.isTagged = isTagged;
    }

    /**
     * Tags the task on the given TaskList.
     * 
     * @param tlist the TaskList on which the command is executed.
     * @throws PandaException if an error occurs during execution.
     */
    public void execute(TaskList tlist) throws PandaException {
        assert tlist != null;
        if(idx - 1 >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        if(isTagged) {
            tlist.tag(idx, tag);
        } else {
            tlist.untag(idx, tag);
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
        if(isTagged) {
            tlist.tag(idx, tag);
        } else {
            tlist.untag(idx, tag);
        }
        cacheFile.save(tlist);
        return isTagged 
            ? String.format("Nice! I've tagged this task as [%s]:\n  " + tlist.getTaskString(idx), tag)
            : String.format("Sure! I've untagged [%s] from this task:\n  " + tlist.getTaskString(idx), tag);
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

        if (!(o instanceof ModifyTagCommand)) {
            return false;
        }
         
        ModifyTagCommand c = (ModifyTagCommand) o;

        return idx == c.idx && isTagged == c.isTagged;
    }
}
