public class AddTodoCommand extends Command {
    public AddTodoCommand(String description) {
        super(description, CommandType.ADD);
    }

    @Override
    public void execute(State state) {
        Task newTodo = new Todo(getText(), false);
        state.addTask(newTodo);
        System.out.println("I added!–\n" + newTodo +  "\n–Mamma-mia!");
    }
}
