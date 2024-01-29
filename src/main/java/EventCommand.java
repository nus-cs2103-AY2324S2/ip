import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventCommand extends Command {
    private final ArrayList<String> argsList;

    public EventCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        int fromIndex = this.argsList.indexOf("/from");
        int toIndex = this.argsList.indexOf("/to");

        if (this.argsList.size() == 1 || fromIndex == 1 || toIndex == 1) {
            throw new NollidException("Event description cannot be empty!\n"
                    + Event.USAGE_HINT);
        }

        if (fromIndex == -1 || fromIndex == this.argsList.size() - 1 || fromIndex == toIndex - 1) {
            throw new NollidException("Please enter the start of your event!\n"
                    + Event.USAGE_HINT);
        }

        if (toIndex == -1 || toIndex == this.argsList.size() - 1 || toIndex == fromIndex - 1) {
            throw new NollidException("Please enter the end of your event!\n"
                    + Event.USAGE_HINT);
        }

        StringBuilder taskDescription = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        // Deal with the user sending "/from" before "/to" or vice versa
        if (fromIndex < toIndex) {
            extractEventInfo(this.argsList, fromIndex, toIndex, taskDescription, from, to);
        } else {
            extractEventInfo(this.argsList, toIndex, fromIndex, taskDescription, to, from);
        }

        try {
            LocalDateTime fromDateTime = Task.getLocalDateTimeFromString(from.toString());
            LocalDateTime toDateTime = Task.getLocalDateTimeFromString(to.toString());
            Event task = new Event(taskDescription.toString(), fromDateTime, toDateTime);
            tasks.add(task);

            String message = "Alright, added:\n" + "\t" + task + "\n";
            message += tasks.summary();
            ui.sendMessage(message);
            storage.update(tasks);
        } catch (DateTimeParseException e) {
            throw new NollidException("Unrecognized start/end format\n"
                    + Event.USAGE_HINT);
        }
    }

    /**
     * Saves the appropriate data in the supplied StringBuilders, given the index of the '/from' and '/to' arguments
     * in the user input.
     */
    private void extractEventInfo(ArrayList<String> userInputAsList, int fromIndex, int toIndex,
                                  StringBuilder taskDescription, StringBuilder from, StringBuilder to) {
        for (int i = 1; i < fromIndex; i++) {
            if (i != fromIndex - 1) {
                taskDescription.append(userInputAsList.get(i)).append(" ");
            } else {
                taskDescription.append(userInputAsList.get(i));
            }
        }

        for (int i = fromIndex + 1; i < toIndex; i++) {
            if (i != toIndex - 1) {
                from.append(userInputAsList.get(i)).append(" ");
            } else {
                from.append(userInputAsList.get(i));
            }
        }

        for (int i = toIndex + 1; i < userInputAsList.size(); i++) {
            if (i != userInputAsList.size() - 1) {
                to.append(userInputAsList.get(i)).append(" ");
            } else {
                to.append(userInputAsList.get(i));
            }
        }
    }
}
