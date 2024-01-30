public class ByeCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Bye. Hope to see you again soon!");
            storage.save(taskList);
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return true;
    }
}
