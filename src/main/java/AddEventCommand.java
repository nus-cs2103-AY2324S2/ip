public class AddEventCommand extends AddCommand {
    private final String startDate;
    private final String endDate;

    AddEventCommand(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    void execute(TaskList taskList) throws MikeException {
        Task newTask = new Event(description, startDate, endDate);
        taskList.add(newTask);
        respond(taskList, newTask);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
