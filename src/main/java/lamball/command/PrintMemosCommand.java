package lamball.command;

import lamball.memo.MemoList;

/**
 * Command that prints the list of memos
 */
public class PrintMemosCommand extends Command {
    private MemoList memoList;

    /**
     * Constructor for command.
     *
     * @param memos MemoList to print from.
     */
    public PrintMemosCommand(MemoList memos) {
        this.memoList = memos;
    }

    @Override public boolean run() {
        memoList.printMemos();
        return true;
    }
}
