package duke;

/**
 * Represents tasks entered and stored in the application
 */

public class Todo extends Task {

    /**
     * Constructor for Todo task
     *
     * @param description description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor used to duplicate a Todo object
     * Use case: For undo operations by recording and storing previous task
     * @param todo
     */
    public Todo(Todo todo) {
        super(todo);
    }

    /**
     * Constructor for Todo task with specified completion status.
     *
     * @param description description of the Todo task
     * @param isComplete      completion status of the Todo task
     */
    public Todo(String description, Boolean isComplete) {
        super(description, isComplete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts a Todo object to a stored Todo task
     *
     * @param objTodo Todo task object
     * @return the string representation of a Todo task that will be stored
     */
    public static String todoStorage(Todo objTodo) {
        String done = objTodo.isComplete ? "1" : "0";
        String description = objTodo.taskDescription;
        return "T | " + done + " | " + description; //To follow the task format
    }

    /**
     * Converts a stored Todo task to a Todo object.
     *
     * @param storeTodo string representation of a stored Todo task
     * @return the Todo object
     */
    public static Todo todoOutput(String storeTodo) {
        String[] para = storeTodo.split(" \\| ");
        Boolean isCompleted = para[1].equals("1") ? true : false;
        String description = para[2];
        return new Todo(description, isCompleted);
    }

    public static void main(String[] args) {
        String store = "T | 0 | 1 | Testing Todo"; //Sample task for testing
        Todo storeTask = Todo.todoOutput(store);
        storeTask.markDone();
        System.out.println(storeTask);
        System.out.println(Todo.todoStorage(storeTask));
    }
}
