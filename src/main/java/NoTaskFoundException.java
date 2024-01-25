public class NoTaskFoundException extends DukeException {
    private String taskId;

    public NoTaskFoundException(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return String.format("%s The number '%s' does not correspond to any task ;(\nPlease try another number.\n",
                super.toString(),
                taskId);
    }
}
