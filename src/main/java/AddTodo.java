public class AddTodo extends Command{
    private String description;
    public AddTodo(Parser.Cmd type, String description){
        super(type);
        this.description = description;
    }
    @Override
    public void run(TaskList taskList){
        Todo todo = new Todo(this.description);
        String[] data = {this.description};
        taskList.addTask(todo, "todo", data);
    }
}
