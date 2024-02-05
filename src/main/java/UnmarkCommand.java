public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(this.index);
            storage.saveToFile(taskList);
        } catch (ChatBotException e) {
            System.out.println("\tAn error occurred when unmarking");
        }
    }
}
