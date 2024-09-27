package lamball.command;

import lamball.memo.MemoList;

/**
 * Command to delete memo from list
 */
public class MemoDelCommand extends Command {

    private int idx;
    private MemoList memos;

    /**
     * Constructor for command.
     *
     * @param idx Index of memo to delete.
     * @param memos List to delete from
     */
    public MemoDelCommand(int idx, MemoList memos) {
        this.idx = idx;
        this.memos = memos;
    }

    @Override
    public boolean run() {
        memos.delMemo(idx);
        return true;
    }
}
