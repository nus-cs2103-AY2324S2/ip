public abstract class AddCommand extends Command {

    private TaskList taskList;

    public AddCommand(TaskList tasklist) {
        this.taskList = tasklist;
    }

    public static String getUsage() {
        return Command.getUsage() + " add <type> ...";
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

}
