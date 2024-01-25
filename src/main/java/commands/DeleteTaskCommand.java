public class DeleteTaskCommand extends Command {
    int index;
    public DeleteTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList.deleteTask(this.index);
            storage.saveTasks(taskList);
        } catch (DukeException e) {
            System.out.println("\tAn error occurred when deleting");
        }
    }
}
