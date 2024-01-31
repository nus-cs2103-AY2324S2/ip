public class DeleteCommand extends Command {

	private int deleteIndex;

	public DeleteCommand(int deleteIndex) {
		this.deleteIndex = deleteIndex;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		try {
			Task toRemove = tasks.deleteTask(deleteIndex);
			ui.deleteTask(toRemove, tasks.getNumTasks());
			storage.saveTasks(tasks);
		} catch (Exception e) {
			throw new DukeException(e.getMessage());
		}
	}

}