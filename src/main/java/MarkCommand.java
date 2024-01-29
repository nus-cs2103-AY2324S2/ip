public class MarkCommand extends Command {
    public MarkCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     * @throws ChatBotParameterException
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = taskList.markTaskAsDone(parameters);
            ui.showMarkedTask(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
