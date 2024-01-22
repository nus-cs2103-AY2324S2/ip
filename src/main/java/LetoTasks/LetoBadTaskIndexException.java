package LetoTasks;

public class LetoBadTaskIndexException extends LetoInvalidTaskException {
    public LetoBadTaskIndexException(int CurrentIndex) {
        super(
            "Bad task index number, should be a integer more than 0 and less than: " +
            (CurrentIndex+1) +
            " see /list for all the tasks");
    }
}
