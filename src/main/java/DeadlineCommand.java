public class DeadlineCommand extends Command{
    private String description;
    private String by;

    DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }


    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.taskResponse(deadline, tasks);
        storage.saveList(tasks.getTasks());
    }
}
