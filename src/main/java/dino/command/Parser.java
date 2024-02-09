package dino.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;
import dino.task.ToDo;

/**
 * Handles the parsing of user commands and creating tasks based on the input.
 */
public class Parser {

    private TaskList tasks;

    /**
     * Constructs a new Parser instance with the specified TaskList, Ui, and Scanner.
     *
     * @param tasks The TaskList to be operated on.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Handles the creation of tasks based on user input for tasks like ToDo, Deadline, and Event.
     *
     * @param taskType The type of the task (ToDo, Deadline, or Event).
     * @param description String description of the task.
     * @return String representation of new task.
     */
    public String handleTaskCreation(Dino.TaskType taskType, String description) {
        StringBuilder printTask = new StringBuilder();

        try {
            if (description.isEmpty()) {
                throw new DinoException("Description cannot be empty.");
            }

            tasks.addTask(createTaskFromInput(taskType, description));

            printTask.append("Okay.\n");
            printTask.append("  ").append(tasks.get(tasks.size() - 1)).append("\n");
            printTask.append("Now you have ").append(tasks.size()).append(" in the list.\n");
        } catch (DinoException e) {
            printTask.append("Error: ").append(e.getMessage());
        }
        return printTask.toString();
    }

    /**
     * Creates a Task object based on the provided task type and task details.
     *
     * @param taskType     The type of the task (ToDo, Deadline, or Event).
     * @param taskDetails  The details of the task.
     * @return A Task object representing the created task.
     * @throws DinoException If there is an error creating the task.
     */
    public Task createTaskFromInput(Dino.TaskType taskType, String taskDetails) throws DinoException {
        switch (taskType) {
        case TODO:
            return new ToDo(taskDetails);

        case DEADLINE:
            String[] deadlineParts = taskDetails.split("/by");
            if (deadlineParts.length != 2) {
                throw new DinoException("Invalid input format for deadline. "
                        + "Please use: deadline <deadline name> /by <time>");
            }
            String deadlineName = deadlineParts[0].trim();
            String deadlineTimeString = deadlineParts[1].trim();
            if (deadlineName.isEmpty() || deadlineTimeString.isEmpty()) {
                throw new DinoException("Deadline name and time cannot be empty.");
            }

            try {
                return new Deadline(deadlineName, parseStringToTime(deadlineTimeString));
            } catch (DateTimeParseException e) {
                throw new DinoException("Error parsing deadline date and time: " + e.getMessage());
            }

        case EVENT:
            String[] eventParts = taskDetails.split("/from|/to");
            if (eventParts.length != 3) {
                throw new DinoException("Invalid input format for event. "
                        + "Please use: event <event name> /from <time> /to <time>");
            }
            String eventName = eventParts[0].trim();
            String startTimeString = eventParts[1].trim();
            String endTimeString = eventParts[2].trim();
            if (eventName.isEmpty() || startTimeString.isEmpty() || endTimeString.isEmpty()) {
                throw new DinoException("Event name, start time, and end time cannot be empty.");
            }

            try {
                LocalDateTime startTime = parseStringToTime(startTimeString);
                LocalDateTime endTime = parseStringToTime(endTimeString);
                return new Event(eventName, startTime, endTime);
            } catch (DateTimeParseException e) {
                throw new DinoException("Error parsing event date and time: " + e.getMessage());
            }

        default:
            throw new DinoException("Unknown task type: " + taskType);
        }
    }


    /**
     * Parses a string representing date and time into a LocalDateTime object.
     *
     * @param time The string representation of date and time.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public LocalDateTime parseStringToTime(String time) {
        assert time != null : "Time cannot be null";
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH);

        LocalDateTime deadlineTime;
        if (time.contains(" ")) {
            deadlineTime = LocalDateTime.parse(time, dateTimeFormatter);
        } else {
            deadlineTime = LocalDateTime.of(LocalDate.parse(time, dateOnlyFormatter), LocalTime.MIDNIGHT);
        }
        return deadlineTime;
    }

    /**
     * Parses a string representing date into a formatted string with a specific pattern.
     *
     * @param time The string representation of date.
     * @return A formatted string representing the parsed date.
     */
    public String parseStringToNum(String time) {
        assert time != null : "Time cannot be null";
        time = time.trim();
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);

        LocalDateTime deadlineTime;
        if (time.contains(" ")) {
            deadlineTime = LocalDateTime.parse(time, dateTimeFormatter);
        } else {
            deadlineTime = LocalDateTime.of(LocalDate.parse(time, dateOnlyFormatter), LocalTime.MIDNIGHT);
        }

        DateTimeFormatter resultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = deadlineTime.format(resultFormatter);

        if (deadlineTime.toLocalTime() != LocalTime.MIDNIGHT) {
            formattedDate += " " + deadlineTime.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
        }

        return formattedDate;
    }

    /**
     * Prints tasks for a specific date.
     *
     * @param dateString The string representation for user input.
     * @return String representation of tasks for specified date.
     */
    String printTasksForDate(String dateString) {
        assert dateString != null : "Date cannot be null";
        StringBuilder result = new StringBuilder();

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(dateString, dateFormatter);

            result.append("Tasks for ").append(date).append(":\n");

            tasks.getTaskList().stream()
                    .filter(task -> task instanceof Deadline)
                    .map(task -> (Deadline) task)
                    .filter(deadline -> deadline.getDateTime().toLocalDate().equals(date))
                    .forEach(deadline -> result.append(deadline).append("\n"));

            tasks.getTaskList().stream()
                    .filter(task -> task instanceof Event)
                    .map(task -> (Event) task)
                    .filter(event -> event.getStartTime().toLocalDate().equals(date)
                            || event.getEndTime().toLocalDate().equals(date))
                    .forEach(event -> result.append(event).append("\n"));

        } catch (DateTimeParseException e) {
            result.append("Error parsing date: ").append(e.getMessage());
        }
        return result.toString();
    }
}

