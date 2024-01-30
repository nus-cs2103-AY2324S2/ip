package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import KBot.TaskFileManager;
import KBot.TaskManager;
import exceptions.InvalidInputException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class AddTask extends Command {
    private String ins;
    private String info;

    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    public AddTask(String taskInstruction, String taskInfo) {
        this.ins = taskInstruction;
        this.info = taskInfo;
    }

    @Override
    public String execute() throws IOException, InvalidInputException {
        Task t = null;
        switch (ins) {
            case "todo":
                t = new ToDo(info);
                break;
            case "deadline":
                String[] deadlineParts = info.split(" /by ", 2);
                if (deadlineParts.length == 1) { // if there is command but no input
                    throw new InvalidInputException("Invalid parameters for " + ins);
                }
                String dName = deadlineParts[0];
                String dEndTime = deadlineParts[1].trim();
                try {
                    LocalDate deadline = LocalDate.parse(dEndTime, PRINTFORMAT);
                    t = new Deadline(dName, deadline);
                } catch (DateTimeParseException e) {
                    return ("Error while parsing date: Format should be d-M-yy.");
                }
                break;
            case "event":
                String[] eventParts = info.split(" /from ", 2);
                if (eventParts.length == 1) { // if there is command but no input
                    throw new InvalidInputException("Invalid parameters for " + ins);
                }
                String eName = eventParts[0];
                String time = eventParts[1];
                String[] timeParts = time.split(" /to ", 2);
                if (timeParts.length == 1) { // if there is command but no input
                    throw new InvalidInputException("Invalid parameters for " + ins);
                }
                String startTime = timeParts[0].trim();
                String endTime = timeParts[1].trim();
                try {
                    LocalDate start = LocalDate.parse(startTime, PRINTFORMAT);
                    LocalDate end = LocalDate.parse(endTime, PRINTFORMAT);
                    t = new Event(eName, start, end);
                } catch (DateTimeParseException e) {
                    return ("Error while parsing date: Format should be d-M-yy.");
                }
                break;
        }
        if (t != null) {
            TaskManager.getTasks().add(t);
            TaskFileManager.saveTasksToFile(TaskManager.getTasks());
            String response = "Got it. I've added this task:\n" + t + "\n" +
                    "Now you have " + TaskManager.getTasks().size() + " tasks in the list.";
            return (response);
        }
        return "";
    }

}
