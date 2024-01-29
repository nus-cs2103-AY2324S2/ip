public class ListCommand extends Command {
    public ListCommand(String keyword, String parameters) {
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
        ui.showTaskList(taskList);
    }
}
