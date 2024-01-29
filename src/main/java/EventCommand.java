import java.time.format.DateTimeParseException;

public class EventCommand implements Command {

    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] task = input.split("/from");
        String[] time = task[1].split("/to");
        try {
            Task t = new Event(task[0].substring(6).trim(), time[0].trim(), time[1].trim());
            list.add(t);
            ui.showAdded(t, list);
            storage.writeToFile(list);
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
