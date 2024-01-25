public class AddDeadlineCommand extends AddCommand {
    private final String byDate;

    AddDeadlineCommand(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        /*
        TODO:
            1. Refactor for DRY Principle.
         */
        Task newTask = new Deadline(description, byDate);
        taskList.add(newTask);
        respond(taskList, ui, newTask);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
