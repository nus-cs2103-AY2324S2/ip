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
        super(description);
    }

    /**
     * Adds a todo task to the state.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return Text output of the command
     */
    @Override
    public String execute(State state, Ui ui) {
        Task newTodo = new Todo(getText(), false);
        state.addTask(newTodo);
        return "I added!–\n" + newTodo + "\n–Mamma-mia!";
    }
}
