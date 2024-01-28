public class InvalidTaskIndexException extends BobException {
    public InvalidTaskIndexException(String taskIndex) {
        super(Replies.INVALID_TASK_INDEX + taskIndex);
    }
}
