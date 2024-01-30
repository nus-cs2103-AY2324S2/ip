public class EventCommand extends Command {
    private final String DETAILS;

    public EventCommand(String details) {
        this.DETAILS = details;
    }

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        String[] detailArray = DETAILS.split("/");
        String description = detailArray[0].strip();
        String from = detailArray[1].strip();
        String to = detailArray[1].strip();
        System.out.println(from);
        Task event = new Event(description, from, to);
        tasksList.addToTaskList(event);
        ui.showAddTaskMessage(event);
        storage.saveToFile(tasksList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}