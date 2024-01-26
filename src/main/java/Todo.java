public class Todo extends Task {

    /**
     * Default constructor, isDone set to false
     * @param description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
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
        // T | 0 | Buy Bread
        String[] params = dbTodo.split(" \\| ");
        Boolean isDone = params[1].equals("1") ? true : false; // if "1", means isDone
        String desc = params[2];
        return new Todo(desc, isDone);
    }

    /**
     * Converts the a Todo Task to the database representation of Todo
     * @param taskTodo Task the todo Task object
     * @return Task the string rep of Todo in the database
     */
    public static String todo2Db (Todo todoTask) {
        // T | 0 | Buy Bread
        String done = todoTask.isDone ? "1" : "0";
        String desc = todoTask.description;
        return "T" + " | " + done + " | " + desc;
    }

    public static void main(String[] args) {
        String dbTodo = "T | 0 | Buy Bread";
        Todo todoTask = Todo.db2Todo(dbTodo);
        todoTask.markAsDone();
        System.out.println(todoTask);
        System.out.println(Todo.todo2Db(todoTask));
    }
}
