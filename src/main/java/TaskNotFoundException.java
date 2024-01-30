public class TaskNotFoundException extends Exception {
    private int numTasks;

    public TaskNotFoundException(String message, int numTasks) {
        super(message);
        this.numTasks = numTasks;
    }

    @Override
    public String toString() {
        return "The requested task cannot be found. " + (numTasks == 0
                ? "There are no tasks."
                : "Please enter a number equal to or less than " + numTasks + ".");
    }
}
