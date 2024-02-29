package teletubbi;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskList Task list to delete from.
     * @param ui User interface to display message.
     * @param storage Storage where task content is stored.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.get(index - 1);
            taskList.delete(this.index);
            setExit(false);
            int numItems = taskList.size();
            String sOrP = numItems == 1 ? "task" : "tasks";
            return "Noted. I've removed this task:\n" + removedTask.toString()
                    + "\nNow you have " + numItems + " " + sOrP + " in the list.";
        } catch (TeletubbiException e) {
            return e.getMessage();
        }
    }
}
