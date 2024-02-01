public class PrintCommand extends Command {
    public PrintCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int listCount = tasks.getTaskListSize();
        if (listCount == 0) {
            ui.showNoTaskText();
        } else {
            tasks.printTasks(listCount);
        }
    }
}
