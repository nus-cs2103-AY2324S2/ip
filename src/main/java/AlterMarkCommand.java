public class AlterMarkCommand extends Command {
    private int idx;
    private boolean marked;

    public AlterMarkCommand(int idx, boolean marked) {
        this.idx = idx;
        this.marked = marked;
    }

    public void execute(TaskList tlist) throws PandaException {
        if(idx - 1 >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        if(marked) {
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
        if(marked) {
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
}
