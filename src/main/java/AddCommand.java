public class AddCommand extends Command {
    private Task newTask;
    public AddCommand(Task newTask ) {
        this.newTask = newTask;
    }
    public void execute(TaskList listStore, Ui ui, Storage storage) {
        listStore.add(newTask);
        int listCount = listStore.getTaskListSize();
        System.out.println("Got it. I've added this task:\n" + newTask.toString());
        if (listCount == 1) {
        System.out.println("Now you have " + listCount + " task in the list.\n");
        } else {
            System.out.println("Now you have " + listCount + " tasks in the list.\n");
        }
    }
}
