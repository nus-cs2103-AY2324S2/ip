public class IncompleteTaskException extends EggyException {
    public IncompleteTaskException(String taskType) {
        super(" The description of a " + taskType +" cannot be empty.");
    }
}
