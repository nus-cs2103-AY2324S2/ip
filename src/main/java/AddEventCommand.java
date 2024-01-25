public class AddEventCommand extends AddCommand {
    private final String fromDate;
    private final String toDate;

    AddEventCommand(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        /*
        TODO:
            1. Refactor to enforce DRY Principle.
         */
        Task newTask = new Event(description, fromDate, toDate);
        taskList.add(newTask);
        respond(taskList, ui, newTask);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
