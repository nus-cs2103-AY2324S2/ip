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

        String message =
                "Got it, I've added this task:\n  "
                + newTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        ui.display(message);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
