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
    public void execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException {
        Task task = null;
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
        ui.showAddedTask(task);
    }
}
