package lamball.memo;

import java.util.ArrayList;

import lamball.Lamball;
import lamball.Storage;

/**
 * Class to manage memos.
 */
public class MemoList {
    private ArrayList<Memo> memos;
    private Lamball lamb;

    /**
     * Constructor for Memo List.
     */
    public MemoList(Lamball lamb) {
        this.lamb = lamb;
        this.memos = new ArrayList<>();
    }

    /**
     * Returns size of task list.
     *
     * @return Intger value of size of task list.
     */
    public int size() {
        return this.memos.size();
    }

    /**
     * Adds a memo to the list
     *
     * @param mem Memo to add.
     */
    public void addMemo(Memo mem, boolean isInit) {
        memos.add(mem);
        lamb.updateLastDoneTask("Added memo");
        if (!isInit) {
            Storage.writeToFile("/memo.txt", mem.toString());
        }
    }

    /**
     * Removes a memo from list
     *
     * @param idx Index of memo to delete
     */
    public void delMemo(int idx) {
        memos.remove(idx - 1);
        lamb.updateLastDoneTask("Removed memo at index " + idx);
        Storage.deleteLine(idx);
    }

    /**
     * Removes all memos from list
     *
     */
    public void clearMemos() {
        memos = new ArrayList<>();
        lamb.updateLastDoneTask("Cleared memos");
        Storage.deleteAllLines("/memo.txt");
    }

    private void printMemos(ArrayList<Memo> lst) {
        String listStr = "Here aaaaare the memos in your list:";
        for (int i = 0; i < lst.size(); i++) {
            listStr += "\n    " + (i + 1) + ". " + lst.get(i).toString() + "";
        }
        lamb.updateLastDoneTask(listStr);
    }

    /**
     * Default list printing operation.
     *
     * @return Boolean to continue keeping the bot running.
     */
    public boolean printMemos() {
        printMemos(this.memos);
        return true;
    }

}
