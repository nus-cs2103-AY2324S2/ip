package ghbot.executor;

import ghbot.Todo;

public class TodoExecutor extends Executor {
    private String description;
    public TodoExecutor(String description) {
        this.description = description;
    }
    @Override
    public void execute() {
        Todo todo = new Todo(this.description);
        this.taskList.addTask(todo);
        System.out.println("Got it. I've added this task:\n" + todo);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
