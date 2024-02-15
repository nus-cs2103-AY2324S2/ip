package duke.actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
    private String instruction;

    /** Input parameters to the command. */
    private String parameter;

    /** Output format for printing dates. */
    private static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("d-M-yy");

    /**
     * Constructor for AddTask.
     * 
     * @param taskInstruction Takes in an instruction.
     * @param taskInfo        Takes in instruction parameters.
     */
    public AddTask(String instruction, String parameter) {
        this.instruction = instruction;
        this.parameter = parameter;
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
        assert instruction != null && instruction.length() > 0 : "Command is not initialised or is empty.";
        String[] tagSplit = instruction.split("/tags");
        ArrayList<String> tags = new ArrayList<>();
        if (tagSplit.length > 1) {
            ParseTags parsedTags = new ParseTags(tagSplit[1]);
            tags = parsedTags.tagsStringToArray();
        }
        switch (instruction) {
            case "todo":
                t = new ToDo(parameter, tags);
                break;
            case "deadline":
                String[] deadlineParameter = parameter.split(" /by ", 2);
                if (deadlineParameter.length == 1) { // if there is command but no input
                    throw new InvalidInputException("Invalid parameters for " + instruction);
                }
                String deadlineName = deadlineParameter[0];
                String deadlineEndDate = deadlineParameter[1].trim();
                try {
                    LocalDate deadline = LocalDate.parse(deadlineEndDate, PRINTFORMAT);
                    t = new Deadline(deadlineName, deadline, tags);
                } catch (DateTimeParseException e) {
                    return ("Error while parsing date: Format should be d-M-yy.");
                }
                break;
            case "event":
                String[] eventParameter = parameter.split(" /from ", 2);
                if (eventParameter.length == 1) { // if there is command but no input
                    throw new InvalidInputException("Invalid parameters for " + instruction);
                }
                String eventName = eventParameter[0];
                String eventDates = eventParameter[1];
                String[] dateParameters = eventDates.split(" /to ", 2);
                if (dateParameters.length < 2) { // if there are less than 2 dates given
                    throw new InvalidInputException("Invalid parameters for " + instruction);
                }
                String eventStartDate = dateParameters[0].trim();
                String eventEndDate = dateParameters[1].trim();
                try {
                    LocalDate start = LocalDate.parse(eventStartDate, PRINTFORMAT);
                    LocalDate end = LocalDate.parse(eventEndDate, PRINTFORMAT);
                    t = new Event(eventName, start, end, tags);
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
