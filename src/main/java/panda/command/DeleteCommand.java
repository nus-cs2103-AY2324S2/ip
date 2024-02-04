package panda.command;
import panda.exception.PandaException;
import panda.exception.OutOfBoundsException;
import panda.component.*;
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    public void execute(TaskList tlist) throws PandaException{
        if(idx >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        tlist.remove(idx);
        return;
    }

    public void execute(TaskList tlist, Ui ui, Storage cacheFile) throws PandaException {
        if(idx >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        String tString = tlist.taskString(idx);
        tlist.remove(idx);
        cacheFile.save(tlist);
        ui.showReply("OK, I've deleted this task:" 
            + "\n  " + tString 
            + "\nNow you have " + tlist.size() + " tasks in the list.");
        return;
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof DeleteCommand)) {
            return false;
        }
         
        DeleteCommand c = (DeleteCommand) o;

        return idx == c.idx;
    }
}
