public class EventCommand extends Command {
    String[] commandList;

    public EventCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingEventException {
        if (this.commandList.length <= 1) {
            throw new MissingEventException();
        }
        Event currentEvent = new Event(commandList[1], commandList[2], commandList[3]);
        taskList.add(currentEvent);
        System.out.println("Got it. I've added this task:\n  " + currentEvent);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.save(taskList);
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
