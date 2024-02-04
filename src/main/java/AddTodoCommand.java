public class AddTodoCommand extends Command {
    public AddTodoCommand(String description) {
        super(description, CommandType.ADD);
    }

    @Override
    public void execute(State state, Ui ui) {
        Task newTodo = new Todo(getText(), false);
        state.addTask(newTodo);
        ui.say("I added!–\n" + newTodo +  "\n–Mamma-mia!");
    }
}
