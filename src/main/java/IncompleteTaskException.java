public class IncompleteTaskException extends EggyException {
    public IncompleteTaskException(String taskType) {
        super(" The description of " + (taskType.matches("^[aeiouAEIOU].*") ? "an " : "a ") + taskType +" cannot be empty.");
    }
}
