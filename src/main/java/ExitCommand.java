public class ExitCommand extends Command {
    public ExitCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @return
     */
    @Override
    boolean isExit() {
        return true;
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     * @throws ChatBotParameterException
     */


    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        storage.saveTaskListToFile(taskList);
        ui.showFarewell();
    }
}
