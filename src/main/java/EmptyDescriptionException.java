public class EmptyDescriptionException extends BobException {
    public EmptyDescriptionException(String taskType) {
        super(String.format(Ui.EMPTY_DESCRIPTION, taskType));
    }
}
