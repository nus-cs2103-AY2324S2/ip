public class DeleteCommand extends Command {
    public DeleteCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     * @throws ChatBotParameterException
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException {
        try {
            Task task = taskList.deleteTask(parameters);
            ui.showDeletedTask(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
