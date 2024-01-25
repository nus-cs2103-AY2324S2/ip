public class AddTodoCommand extends AddCommand {
    AddTodoCommand(String description) {
        super(description);
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        /*
        TODO:
            1. Ensure that there are no duplicates
         */
        Task newTask = new Todo(description);
        taskList.add(newTask);
        respond(taskList, ui, newTask);
    }

    @Override
    boolean isExit() {
        return false;
    }

}
