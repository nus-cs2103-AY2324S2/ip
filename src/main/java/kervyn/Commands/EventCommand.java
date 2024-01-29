package kervyn.Commands;

import kervyn.Tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

public class EventCommand extends Command {
    private String userInput;
    public EventCommand(TaskList taskList, String userInput) {
        super("Event", taskList);
        this.userInput = userInput;
    }
    private Event getProcessedEvent(String userInput) {
        // Input Format: event project meeting /from Mon 2pm /to 4pm
        // Output Format: [E][ ] project meeting (from: Mon 2pm to: 4pm)
        try {
            String[] eventProcessedInput = userInput.split("/");
            if (Objects.equals(eventProcessedInput[1], "") || Objects.equals(eventProcessedInput[2], "")) {
                System.out.println("\tThe description/startDate/endDate for an event cannot be empty. Please try again.");
                return null;
            }
            String[] eventDescriptionArray = eventProcessedInput[0].split(" ");
            String[] eventDateArray = userInput.split("/from");
            String eventStartDateStr = eventDateArray[1].split("/to")[0].trim();
            String eventEndDateStr = eventDateArray[1].split("/to")[1].trim();
            StringBuilder eventDescription = new StringBuilder();

            for (int i = 1; i < eventDescriptionArray.length; i++) {
                eventDescription.append(" ");
                eventDescription.append(eventDescriptionArray[i]);
            }
            String convertedStartDate = convertDate(eventStartDateStr);
            String convertedEndDate = convertDate(eventEndDateStr);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            // No longer String dates
            LocalDateTime startDate = LocalDateTime.parse(convertedStartDate, formatter);
            LocalDateTime endDate = LocalDateTime.parse(convertedEndDate, formatter);


            taskAdded();
            return new Event(eventDescription.toString(), false, startDate, endDate);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            System.out.println("\tPlease provide the start date / end date in the required format that looks like dd-MM-yyyy HHmm.");
            return null;
        }
    }
    private void eventTaskTextDisplay(Event event, ArrayList<Task> userTasks) {
        System.out.println("\t[" + event.getCapitalType() + "]" + "[ ]" + event.getDescription() + " (from: " + event.getStartDate() + " to: " + event.getEndDate() + ")");
        System.out.println("\tNow you have " + userTasks.size() + " tasks in the list.");
    }

    @Override
    public void executeCommand() {
        Event newEvent = getProcessedEvent(this.userInput);
        if (newEvent != null) {
            this.taskList.addTask(newEvent);
            eventTaskTextDisplay(newEvent, this.taskList.getTaskList());
        }
    }
}
