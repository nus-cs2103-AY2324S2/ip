package duke;

/**
 * Task that has a description only.
 */
public class Todo extends Task {

    /**
     * Default constructor, isDone set to false.
     * @param description of Todo
     */
    public Todo(String description, int priority) {
        super(description, priority);
    }

    /**
     * Overloaded constructor, isDone is set.
     * @param description of Todo
     */
    public Todo(String description, Boolean isDone, int priority) {
        super(description, isDone, priority);
    }

    /**
     * Constructor used to clone a Todo object
     * @param todo
     */
    public Todo(Todo todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the database representation of Todo to a Todo Task
     * @param dbTodo the string rep of Todo in the database
     * @return Task the todo Task object
     */
    public static Todo db2Todo(String dbTodo) {
        // T | 0 | 1 | Buy Bread
        String[] params = dbTodo.split(" \\| ");
        Boolean isDone = params[1].equals("1") ? true : false; // if "1", means isDone
        int priority = Integer.parseInt(params[2]);
        String desc = params[3];
        return new Todo(desc, isDone, priority);
    }

    /**
     * Converts the a Todo Task to the database representation of Todo.
     * @param todoTask is the todo Task object
     * @return Task the string rep of Todo in the database
     */
    public static String todo2Db(Todo todoTask) {
        // T | 0 | 1 | Buy Bread
        String done = todoTask.isDone ? "1" : "0";
        String desc = todoTask.description;
        int priority = todoTask.priority;
        return "T" + " | " + done + " | " + priority + " | " + desc;
    }

    public static void main(String[] args) {
        String dbTodo = "T | 0 | 1 | Buy Bread";
        Todo todoTask = Todo.db2Todo(dbTodo);
        todoTask.markAsDone();
        System.out.println(todoTask);
        System.out.println(Todo.todo2Db(todoTask));
    }
}
