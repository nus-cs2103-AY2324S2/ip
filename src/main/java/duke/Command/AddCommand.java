package duke.Command;

import duke.Task.DeadlineTask;
import duke.Task.EventTask;
import duke.Task.Task;
import duke.TaskList;
import duke.DukeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import duke.Task.ToDoTask;
import java.time.format.DateTimeParseException;
/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    /**
     * Executes the add command.
     *
     * @param taskList the task list to add the task to
     * @param command the command string
     * @return the result message after adding the task
     * @throws DukeException if there is an error executing the command
     */
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("Please provide a description for the task.");
        }
        String taskType = parts[0].trim();
        String taskDetails = parts[1].trim();

        Task newTask;
        switch (taskType) {
            case "todo":
                newTask = new ToDoTask(taskDetails);
                break;
            case "deadline":
                String[] deadlineParts = taskDetails.split("/by");
                if (deadlineParts.length < 2) {
                    throw new DukeException("Please provide both description and deadline for the task in the format: deadline <description> /by <deadline>");
                }
                String deadlineDescription = deadlineParts[0].trim();
                String deadlineTime = deadlineParts[1].trim();
                try {
                    LocalTime deadline = LocalTime.parse(deadlineTime, DateTimeFormatter.ofPattern("HHmm"));
                    newTask = new DeadlineTask(deadlineDescription, deadline);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid deadline format. Please use HHmm format for the deadline.");
                }
                break;
            case "event":
                String[] eventParts = taskDetails.split("/at");
                if (eventParts.length < 2) {
                    throw new DukeException("Please provide both description and time for the event in the format: event <description> /at <start time>-<end time>(HHmm)");
                }
                String eventDescription = eventParts[0].trim();
                String[] eventTimeParts = eventParts[1].split("-");
                if (eventTimeParts.length < 2) {
                    throw new DukeException("Please provide both start and end times for the event in the format: event <description> /at <start time>-<end time>(HHmm)");
                }
                String startTime = eventTimeParts[0].trim();
                String endTime = eventTimeParts[1].trim();
                try {
                    LocalTime eventStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
                    LocalTime eventEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));
                    newTask = new EventTask(eventDescription, eventStartTime, eventEndTime);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid time format. Please use HHmm format for the time.");
                }
                break;

            default:
                throw new DukeException("Invalid task type.");
        }

        taskList.addTask(newTask);
        return "Got it. I've added this task:\n" + newTask + "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
    }
}