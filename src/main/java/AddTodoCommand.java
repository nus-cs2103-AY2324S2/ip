public class AddTodoCommand extends AddCommand {
    AddTodoCommand(String description) {
        super(description);
    }

    @Override
    void execute(TaskList taskList) throws MikeException {
        Task newTask = new Todo(description);
        taskList.add(newTask);
        respond(taskList, newTask);
    }

    @Override
    boolean isExit() {
        return false;
    }

}
