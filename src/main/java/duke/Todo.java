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
    public Todo(String description) {super(description);}

    /**
     * Constructor for Todo task with specified completion status.
     *
     * @param description description of the Todo task
     * @param isDone      completion status of the Todo task
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructor used to clone a Todo object.
     *
     * @param todo the Todo object to clone
     */
    public Todo(Todo todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the database representation of a Todo task to a Todo object.
     *
     * @param dbTodo the string representation of the Todo task in the database
     * @return the Todo object
     */
    public static Todo db2Todo(String dbTodo) {
        // T | 0 | Buy Bread
        String[] para = dbTodo.split(" \\| ");
        Boolean isDone = para[1].equals("1") ? true : false;
        String description = para[2];
        return new Todo(description, isDone);
    }

    /**
     * Converts a Todo object to its database representation.
     *
     * @param todoTask the Todo task object
     * @return the string representation of the Todo task in the database
     */
    public static String todo2Db(Todo todoTask) {
        // T | 0 | Buy Bread
        String done = todoTask.isTaskDone ? "1" : "0";
        String description = todoTask.taskDescription;
        return "T | " + done + " | " + description;
    }

    public static void main(String[] args) {
        String dbTodo = "T | 0 | Buy Bread";
        Todo todoTask = Todo.db2Todo(dbTodo);
        todoTask.markDone();
        System.out.println(todoTask);
        System.out.println(Todo.todo2Db(todoTask));
    }
}
