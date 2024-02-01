package duke.actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidInputException;
import duke.kbot.TaskFileManager;
import duke.kbot.TaskManager;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * An add task command that adds a task onto the tasks array.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class AddTask extends Command {
    /** Instruction or type of command. */
    private String ins;

    /** Input parameters to the command. */
    private String info;

    /** Output format for printing dates. */
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    /**
     * Constructor for AddTask.
     * 
     * @param taskInstruction Takes in an instruction.
     * @param taskInfo        Takes in instruction parameters.
     */
    public AddTask(String taskInstruction, String taskInfo) {
        this.ins = taskInstruction;
        this.info = taskInfo;
    }

    /**
     * Executes the adding of a new Task to the task list.
     * 
     * @throws IOException           Exception for writing and loading from
     *                               memory.
     * @throws InvalidInputException Exception when input parameters are
     *                               invalid.
     */
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
