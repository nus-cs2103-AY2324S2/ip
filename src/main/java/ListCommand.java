public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println(i + 1 + "." + currentTask);
        }
    }

    public boolean isExit() {
        return false;
    }
}
