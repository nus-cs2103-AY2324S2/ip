package dibo;
public class AddCommand extends Command{
    String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showAdded(taskList.addTask(this.fullCommand), taskList.getSize());
        storage.save(taskList);
    }

}
