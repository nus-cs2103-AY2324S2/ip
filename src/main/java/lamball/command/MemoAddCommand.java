package lamball.command;

import lamball.memo.Memo;
import lamball.memo.MemoList;

/**
 * Command to add memo to list.
 */
public class MemoAddCommand extends Command {

    private String memo;
    private MemoList memos;

    /**
     * Constructor for command.
     *
     * @param memo Content of memo.
     * @param memos List to add to.
     */
    public MemoAddCommand(String memo, MemoList memos) {
        this.memo = memo;
        this.memos = memos;
    }

    @Override
    public boolean run() {
        memos.addMemo(new Memo(memo), false);
        return true;
    }
}
