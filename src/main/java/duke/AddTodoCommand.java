package duke;

/**
 * Class that represents the command to add a todo task.
 */
public class AddTodoCommand extends Command {
    /**
     * Constructor for the add todo command.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        super(description, CommandType.ADD);
    }

    /**
     * Adds a todo task to the state.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     */
    @Override
    public void execute(State state, Ui ui) {
        Task newTodo = new Todo(getText(), false);
        state.addTask(newTodo);
        ui.say("I added!–\n" + newTodo + "\n–Mamma-mia!");
    }
}
