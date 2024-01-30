import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddEventCommand extends Command {

    private String input;

    public AddEventCommand(String userInput) {
        this.input = userInput;
    }
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the description!");
        }

        String[] eventSplit = input.split("/");
        if (eventSplit.length < 3) {
            throw new DukeException("Invalid format for new Event!");
        }

        String name = eventSplit[0].substring(6).trim();
        String start = eventSplit[1].substring(5).trim();
        String end = eventSplit[2].substring(3).trim();
        LocalDateTime startDT = DateTimeManager.convertStringToLocalDateTime(start);
        LocalDateTime endDT = DateTimeManager.convertStringToLocalDateTime(end);
        Event newEvent = new Event(name, false, startDT, endDT);
        tasks.addTask(newEvent);
        ArrayList<Task> newEventList = new ArrayList<>();
        newEventList.add(newEvent);
        storage.writeArrayListToFile(newEventList, false);
        ui.printAnyStatement("Got it. I've added this task:");
        ui.printAnyStatement(newEvent.toString());
        ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
