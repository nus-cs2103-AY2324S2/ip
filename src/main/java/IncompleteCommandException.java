public class IncompleteCommandException extends Exception {
    private String taskType;
    public IncompleteCommandException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + this.taskType + " command cannot be empty.";
    }
}