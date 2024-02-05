public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTask(this.index);
            storage.saveToFile(taskList);
        } catch (ChatBotException e) {
            System.out.println("\tAn error occurred when marking tasks");
        }
    }
}
