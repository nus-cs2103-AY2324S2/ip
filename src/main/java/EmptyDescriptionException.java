public class EmptyDescriptionException extends BobException {
    public EmptyDescriptionException(String taskType) {
        super(String.format(Replies.EMPTY_DESCRIPTION, taskType));
    }
}
