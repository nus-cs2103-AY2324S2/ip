package me.ruibin.leto.tasklist;

/** Exception indicating invalid index for command. */
public class BadTaskIndexException extends InvalidTaskException {
    /**
     * Constructor for BadTaskIndexException to tell range that the index they entered
     * should fall under.
     * Also give an appropriate help or what to do message.
     *
     * @param currentIndex current max length of task list.
     */
    public BadTaskIndexException(int currentIndex) {
        super(
                "Bad task index number, should be a integer more than 0 and less than: "
                        + (currentIndex + 1) + " see /list for all the tasks");
    }
}
