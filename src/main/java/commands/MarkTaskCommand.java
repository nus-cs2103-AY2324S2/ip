public class MarkTaskCommand extends Command {
    int index;
    public MarkTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList.markTask(this.index);
            storage.saveTasks(taskList);
        } catch (DukeException e) {
            System.out.println("\tAn error occurred when marking tasks");
        }
    }
}
