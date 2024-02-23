package asher.commands;

import asher.BotException;
import asher.tasks.Event;
import asher.tasks.TaskList;
import asher.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a command to create an event task.
 */
public class EventCommand extends Command {

    /**
     * Constructs an Event Command object with the given input string.
     *
     * @param input The input string for the Event Command.
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * Parses the input string to create an Event object.
     *
     * @return The created Event object.
     * @throws BotException BotException is thrown if there is invalid input found.
     */
    public Event createEventCommand() throws BotException {
        if (input.length() < 7) {
            throw new BotException("Event description cannot be empty!");
        }

        int split1 = input.indexOf("/from");
        int split2 = input.indexOf("/to");
        if (split1 == -1 || split2 == -1) {
            throw new BotException("Start time cannot be empty!");
        }

        if (split2 + 4 >= input.length()) {
            throw new BotException("End time cannot be empty!");
        }

        String description = input.substring(6, split1).trim();

        if (description.isEmpty()) {
            throw new BotException("Description not found!");
        }

        String start = input.substring(split1 + 6, split2).trim();
        String[] startParts = start.split(" ", 2);
        String startDateInString = startParts[0].trim();
        LocalDate startDate = LocalDate.parse(startDateInString);
        String startTimeInString = startParts[1].trim();
        LocalTime startTime = LocalTime.parse(startTimeInString);

        String end = input.substring(split2 + 4).trim();
        String[] endParts = end.split(" ", 2);
        String endDateInString = endParts[0].trim();
        LocalDate endDate = LocalDate.parse(endDateInString);
        String endTimeInString = endParts[1].trim();
        LocalTime endTime = LocalTime.parse(endTimeInString);

        if (start.isEmpty() || end.isEmpty()) {
            throw new BotException("Description, StartTiming or EndTiming not found!");
        }

        if ((endDate.isBefore(startDate)) || ((endDate.isEqual(startDate)) && (endTime.isBefore(startTime)))) {
            throw new BotException("Start Date/Time should be before End Date/Time!");
        }

        return new Event(description, startDate, startTime, endDate, endTime);
    }


    /**
     * Executes the Event Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for Event Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Event event = createEventCommand();
            taskList.addTask(event);
            String addTaskMessage = ui.showAddTaskMessage(event);
            int totalTask = taskList.getSize();
            String numberOfTaskMessage = ui.showNumberOfTaskInListMessage(totalTask);

            return addTaskMessage + "\n" + numberOfTaskMessage;
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
