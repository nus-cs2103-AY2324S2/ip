package asher.Commands;

import asher.BotException;
import asher.Tasks.Event;
import asher.Tasks.TaskList;
import asher.Ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventCommand extends Command {

    public EventCommand(String input) {
        super(input);
    }

    public Event createEventCommand() throws BotException {
        int split1 = input.indexOf("/from");
        int split2 = input.indexOf("/to");
        assert split1 != -1 && split2 != -1 : "Start and End time not found!";
        assert split2 + 4 < input.length() : "End time not found!";

        String description = input.substring(6, split1).trim();

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

        if ((endDate.isBefore(startDate)) || ((endDate.isEqual(startDate)) && (endTime.isBefore(startTime)))) {
            throw new BotException("Start Date/Time is after End Date/Time!");
        }

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new BotException("Description, StartTiming or EndTiming not found!");
        }

        return new Event(description, startDate, startTime, endDate, endTime);
    }

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
