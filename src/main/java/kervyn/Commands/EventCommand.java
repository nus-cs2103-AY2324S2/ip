package kervyn.Commands;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.FXControls.DialogBox;
import kervyn.Tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the "Event" command in the application, used to create and add an Event task to the TaskList.
 */
public class EventCommand extends Command {
    private String userInput;
    private Image kervynImage;

    private VBox dialogContainer;

    /**
     * Constructs an EventCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input string containing the event details.
     */
    public EventCommand(TaskList taskList, String userInput, Image kervynImage, VBox dialogContainer) {
        super("Event", taskList, kervynImage, dialogContainer);
        this.userInput = userInput;
        this.kervynImage = kervynImage;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Processes the user input and creates an Event task.
     * The method parses the input, converts the start and end dates, and constructs an Event object.
     *
     * @param userInput The user input string to process.
     * @return An Event object, or null if the input format is invalid.
     */
    private Event getProcessedEvent(String userInput) {
        // Input Format: event project meeting /from Mon 2pm /to 4pm
        // Output Format: [E][ ] project meeting (from: Mon 2pm to: 4pm)
        try {
            String[] eventProcessedInput = userInput.split("/");
            if (Objects.equals(eventProcessedInput[1], "") || Objects.equals(eventProcessedInput[2], "")) {
                this.dialogContainer.getChildren().add(
                        DialogBox.getKervynDialog("\tThe description/startDate/endDate for an event cannot be empty. Please try again.", this.kervynImage)
                );
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
            this.dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tPlease provide the start date / end date in the required format that looks like dd-MM-yyyy HHmm.", this.kervynImage)
            );
            return null;
        }
    }

    /**
     * Displays information about the newly added Event task.
     *
     * @param event The Event task that was added.
     * @param userTasks The current list of tasks, including the newly added event task.
     */
    private void eventTaskTextDisplay(Event event, ArrayList<Task> userTasks) {
        this.dialogContainer.getChildren().add(
                DialogBox.getKervynDialog(event.toString(), this.kervynImage)
        );
        this.dialogContainer.getChildren().add(
                DialogBox.getKervynDialog("\tNow you have " + userTasks.size() + " tasks in the list.", this.kervynImage)
        );
    }

    /**
     * Executes the "Event" command.
     * This method processes the user input, creates a new Event task, adds it to the task list, and displays a confirmation message.
     */
    @Override
    public void executeCommand() {
        Event newEvent = getProcessedEvent(this.userInput);
        if (newEvent != null) {
            this.taskList.addTask(newEvent);
            eventTaskTextDisplay(newEvent, this.taskList.getTaskList());
        }
    }
}
