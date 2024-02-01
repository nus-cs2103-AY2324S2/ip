/**
 * DeleteTaskCommand handles the executing of deletion of tasks.
 */
public class DeleteTaskCommand extends TaskCommand {
    /**
     * Constructs a new DeleteTaskCommand.
     *
     * @param param the parameter of the command.
     * 
     * @return a new DeleteTaskCommand.
     */
    public DeleteTaskCommand(String param) {
        this.param = param;
    }

    @Override
    public void Execute() throws InvalidParamException {
        if (Integer.parseInt(param) >= 1) {
            if (StorageManager.getInstance().deleteTask(Integer.parseInt(param) - 1)) {
                UiManager.getInstance().printDeletingTask();
            } else {
                throw new InvalidParamException("Cannot delete task", null);
            }
        } else {
            throw new InvalidParamException("Cannot delete task", null);
        }
    }
}
