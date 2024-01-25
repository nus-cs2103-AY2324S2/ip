public class AddDeadlineCommand extends AddCommand {
    private final String deadline;

    AddDeadlineCommand(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        Task newTask = new Deadline(description, deadline);
        taskList.add(newTask);
        respond(taskList, ui, newTask);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
