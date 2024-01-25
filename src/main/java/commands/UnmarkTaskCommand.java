public class UnmarkTaskCommand extends Command{
    int index;
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList.unmarkTask(this.index);
            storage.saveTasks(taskList);
        } catch (DukeException e) {
            System.out.println("\tAn error occurred when unmarking");
        }
    }
}
