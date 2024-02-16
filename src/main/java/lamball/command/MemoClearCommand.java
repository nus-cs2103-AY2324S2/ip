package lamball.command;

import lamball.memo.MemoList;

/**
 * Command to clear a list's memos.
 */
public class MemoClearCommand extends Command {

    private MemoList memos;

    /**
     * Constructor for command.
     *
     * @param memos list to clear
     */
    public MemoClearCommand(MemoList memos) {
        this.memos = memos;
    }

    @Override
    public boolean run() {
        memos.clearMemos();
        return true;
    }
}
