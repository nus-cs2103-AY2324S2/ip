public class EmptyDescriptionException extends DukeException{
    private String taskType;

    public EmptyDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("%s The description of the '%s' task cannot be empty ;(\nPlease fill it in.\n",
                super.toString(),
                taskType);
    }
}
