package leto.tasklist;

public class BadTaskIndexException extends InvalidTaskException {
    public BadTaskIndexException(int CurrentIndex) {
        super(
            "Bad task index number, should be a integer more than 0 and less than: " +
            (CurrentIndex+1) +
            " see /list for all the tasks");
    }
}
