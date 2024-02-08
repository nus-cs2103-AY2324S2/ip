package me.ruibin.leto.tasklist;

/** Exception for user entering a bad index for task lists. */
public class BadTaskIndexException extends InvalidTaskException {
    /**
     * Constructor for BadTaskIndexException to tell range that the index they entered
     * should fall under.
     * Also give an appropriate help or what to do message.
     *
     * @param CurrentIndex current max length of task list.
     */
    public BadTaskIndexException(int CurrentIndex) {
        super(
            "Bad task index number, should be a integer more than 0 and less than: "
                    + (CurrentIndex+1) + " see /list for all the tasks");
    }
}
