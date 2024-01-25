public class EmptyDescriptionException extends DukeException {
    String taskType;
    public EmptyDescriptionException(String TaskType) {
        super();
        this.taskType = TaskType;
    }

    @Override
    public String toString() {
        return String.format("You definitely are a genius creating a %s with no description.\nI hope you are genius enough " +
                "to try again with an adequate description\n", this.taskType);
    }
}
