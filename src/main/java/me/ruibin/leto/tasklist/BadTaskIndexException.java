package me.ruibin.leto.tasklist;

public class BadTaskIndexException extends InvalidTaskException {
    public BadTaskIndexException(int currentIndex) {
        super(
            "Bad task index number, should be a integer more than 0 and less than: "
                    + (currentIndex + 1) + " see /list for all the tasks");
    }
}
