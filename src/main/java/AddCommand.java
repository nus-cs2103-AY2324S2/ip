public class AddCommand extends Command{

    protected AddCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * @param storage
     * @param ui
     * @param taskList
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        Task task = null;
        try {

            switch (this.keyword) {
            case "todo":
                task = taskList.addToDo(this.parameters);
                break;
            case "deadline":
                task = taskList.addDeadline(this.parameters);
                break;
            case "event":
                task = taskList.addEvent(this.parameters);
                break;
            default:
                break;

            }
        } catch (ChatBotParameterException e) {
            ui.showError(e.getMessage());
            return;
        }
        ui.showAddedTask(task);
        ui.showTaskListStatus(taskList);
    }
}
