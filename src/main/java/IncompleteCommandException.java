public class IncompleteCommandException extends EggyException {
    public IncompleteCommandException(String commandType) {
        super(" The task number to be " + commandType + (commandType.equals("delete") ? "d" : "ed") + " is not specified.");
    }
}
