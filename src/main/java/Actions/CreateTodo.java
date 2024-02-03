package Actions;

import UI.Duke;
import Tasks.Todo;

/**
 * The `CreateTodo` class implements the Action interface and represents an action to create a new Todo task.
 * It stores the description for the new Todo task and provides a method to execute the action.
 */
public class CreateTodo implements Action {

    private String desc;

    /**
     * Constructs a `CreateTodo` object with the specified description.
     *
     * @param desc The description of the new Todo task.
     */
    public CreateTodo(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the create todo action by creating a new Todo task with the stored description.
     * The new task is then added to the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     */
    @Override
    public void execute(Duke bot) {
        Todo task = new Todo(desc);
        bot.getTaskList().addToList(task);
        System.out.println("Task successfully added!");
    }
}

