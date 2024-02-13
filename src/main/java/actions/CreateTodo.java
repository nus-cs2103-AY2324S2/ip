package actions;

import tasks.Todo;
import ui.Duke;


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
     * @return A message indicating the result of the createTodo action.
     */
    @Override
    public String execute(Duke bot) {
        Todo task = new Todo(desc);
        bot.getTaskList().addToList(task);
        return ("Task successfully added!");
    }
}

