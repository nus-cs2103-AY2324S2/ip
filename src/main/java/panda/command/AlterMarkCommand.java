package panda.command;
import panda.exception.PandaException;
import panda.exception.OutOfBoundsException;
import panda.component.*;
public class AlterMarkCommand extends Command {
    private int idx;
    private boolean isMarked;

    public AlterMarkCommand(int idx, boolean marked) {
        this.idx = idx;
        this.isMarked = isMarked;
    }

    public void execute(TaskList tlist) throws PandaException {
        if(idx - 1 >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        if(isMarked) {
            tlist.mark(idx);
        }
        else {
            tlist.unmark(idx);
        }
    }

    public void execute(TaskList tlist, Ui ui, Storage cacheFile) throws PandaException {
        if(idx - 1 >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        if(isMarked) {
            tlist.mark(idx);
        }
        else {
            tlist.unmark(idx);
        }
        cacheFile.save(tlist);
        ui.showReply("Nice! I've marked this task as done:\n  " + tlist.taskString(idx));
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

        if (!(o instanceof AlterMarkCommand)) {
            return false;
        }
         
        AlterMarkCommand c = (AlterMarkCommand) o;

        return idx == c.idx && isMarked == c.isMarked;
    }
}
