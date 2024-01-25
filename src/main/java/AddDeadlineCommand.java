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
