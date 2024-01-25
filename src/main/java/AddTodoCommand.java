public class AddTodoCommand extends AddCommand {
    AddTodoCommand(String description) {
        super(description);
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        Task newTask = new Todo(description);
        taskList.add(newTask);
        respond(taskList, ui, newTask);
    }

    @Override
    boolean isExit() {
        return false;
    }

}
