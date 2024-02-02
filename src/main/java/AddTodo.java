public class AddTodo extends Command{
    private String description;
    public AddTodo(String description){
        this.description = description;
    }
    @Override
    public void run(TaskList taskList){
        Todo todo = new Todo(this.description);
        taskList.addTask(todo);
    }
}
