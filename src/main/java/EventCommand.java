public class EventCommand extends Command {
    private Event event;

    public EventCommand (Event event) {
        this.event = event;
    }

    @Override
    public void execute(Ui ui) {
        super.taskList.addTask(event);
        ui.printToUser("\tGot it. I've added this task:",
                        "\t" + this.event,
                        super.taskList.getListCounter());
    }
}
