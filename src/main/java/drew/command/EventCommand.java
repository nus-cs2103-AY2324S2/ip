package drew.command;

import java.time.LocalDate;
import java.util.ArrayList;

import drew.storage.Storage;
import drew.storage.TaskList;
import drew.task.Event;
import drew.task.Task;

/**
 * This class represents the Event command.
 */
public class EventCommand extends Command {

    public EventCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        String reply = "";
        ArrayList<Task> ls = tasks.getList();
        int listLength = ls.size();

        int firstBackslashIndex = input.indexOf("/from");
        if (firstBackslashIndex == -1) {
            throw new IllegalArgumentException("Incorrect input. Ensure that start date begins with /from");
        }
        int secondBackslashIndex = input.indexOf("/to", firstBackslashIndex + 5);
        if (secondBackslashIndex == -1) {
            throw new IllegalArgumentException("Incorrect input. Ensure that end date begins with /to");
        }

        String eventDescription = input.substring(6, firstBackslashIndex);
        LocalDate startDate = LocalDate.parse(input.substring(firstBackslashIndex + 5, secondBackslashIndex).trim());
        LocalDate endDate = LocalDate.parse(input.substring(secondBackslashIndex + 3).trim());
        Event newTask = new Event(eventDescription, startDate, endDate);
        ls.add(newTask);

        reply = "Got it. I've added this task:\n";
        reply = reply + newTask.toStatusString() + "\n";
        listLength++;
        reply = reply + String.format("Now you have %d task(s) in the list.", listLength);

        return reply;
    }

    public static boolean isEventCommand(int inputLength, String input) {
        return inputLength >= 5 && input.substring(0, 5).equalsIgnoreCase("event");
    }
}
