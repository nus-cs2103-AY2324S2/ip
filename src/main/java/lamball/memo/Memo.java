package lamball.memo;

import java.util.Date;

/**
 * Basic Memo class
 */
public class Memo {

    private String content;
    private Date lastEdited;

    /**
     * Constructor for a memo.
     *
     * @param content Text content of the memo.
     */
    public Memo(String content) {
        this.content = content;
        this.lastEdited = new Date();
    }

    @Override
    public String toString() {
        return this.content;
    }
}
