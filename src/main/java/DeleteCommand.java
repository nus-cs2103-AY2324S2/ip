public class DeleteCommand extends Command{
    private int id;
    public DeleteCommand(int id) {
        this.id = id;
    }
    @Override
    public String execute(TaskList tasks, StateFile file) {
        String taskString = tasks.toString(this.id);
        if (!tasks.isValidId(this.id)){
            return "Task to delete does not exist.";
        }
        tasks.remove(this.id);
        String saveResult = file.saveState(tasks);
        return "Noted. I've removed this task:\n"
                + "\t"
                + taskString
                + saveResult;
    }
}
