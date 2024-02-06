package duke;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    /** Static method to create a Todo object from a formatted string
     * @param inputString string to parse to Todo object
     * @return Todo object
     */
    public static Todo parseTodoFromString(String inputString) {
        // Assuming the inputString is formatted as "[T][ ] task description"
        String taskDescription = inputString.substring(7); // Extract the task description
        Todo todo = new Todo(taskDescription.trim());

        // Check the status and mark the Todo as done if needed
        if (inputString.charAt(4) == 'X') {
            todo.markDone();
        }
        return todo;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
