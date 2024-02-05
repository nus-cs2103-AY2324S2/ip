package ghbot.executor;

import ghbot.Todo;

/**
 * TodoExecutor Class.
 * Executes "todo" command.
 */
public class TodoExecutor extends Executor {
    private String description;

    /**
     * TodoExecutor Class.
     * @param description The description of the todo task.
     */
    public TodoExecutor(String description) {
        this.description = description;
    }

    /**
     * Prints a string to let user know that the todo task has been added.
     */
    @Override
    public void execute() {
        Todo todo = new Todo(this.description);
        this.taskList.addTask(todo);
        System.out.println("Got it. I've added this task:\n" + todo);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
