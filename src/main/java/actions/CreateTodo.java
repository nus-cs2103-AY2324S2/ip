package actions;

import static actions.CheckDuplicate.checkDuplicate;
import exceptionhandling.DukeException;
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
     * @param command     The command string.
     */
    public CreateTodo(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length <= 1) {
            throw new DukeException("Please write a description for your task!");
        }
        String desc = splitCommand[1];
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
        assert(bot != null);
        assert(bot.getTaskList() != null);
        if (checkDuplicate(bot, this.desc, "T")) {
            return ("There is already a todo with the same name!");
        }
        Todo task = new Todo(desc);
        bot.getTaskList().addToList(task);
        return ("Task successfully added!");
    }
}

