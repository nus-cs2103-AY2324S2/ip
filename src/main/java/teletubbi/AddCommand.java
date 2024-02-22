package teletubbi;

/**
 * Represents the command to add a task.
 */
public class AddCommand extends Command {
    private Task t;
    public AddCommand(Task t) {
        this.t = t;
    }

    /**
     * Executes the adding of a task to the specified tasklist.
     *
     * @param taskList Tasklist to be added to.
     * @param ui User interface.
     * @param storage Storage to save Tasklist content.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.t);
        setExit(false);
        int numItems = taskList.size();
        String sOrP = numItems == 1 ? "task" : "tasks";
        return "Got it. I've added this task:\n" + t.toString()
                + "\nNow you have " + numItems + " " + sOrP + " in the list.";
    }
}
