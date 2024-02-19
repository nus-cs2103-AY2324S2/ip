package ghbot.executor;

import ghbot.task.Todo;

/**
 * TodoExecutor Class.
 * Executes "todo" command.
 */
public class TodoExecutor extends Executor {
    private String description;
    private String executeStr;

    /**
     * TodoExecutor Constructor.
     * @param description The description of the todo task.
     */
    public TodoExecutor(String description) {
        this.description = description;
        this.executeStr = "";
    }

    /**
     * Prints a string to let user know that the todo task has been added.
     */
    @Override
    public String execute() {
        Todo todo = new Todo(this.description);
        this.taskList.addTask(todo);
        this.executeStr = "Got it. I've added this task:\n" + todo + "\n";
        this.executeStr = this.executeStr + "Now you have " + this.taskList.taskSize() + " tasks in the list.";
        return this.executeStr;
    }
}
