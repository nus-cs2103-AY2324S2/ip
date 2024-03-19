package kbot.actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import kbot.exceptions.InvalidInputException;
import kbot.managers.TaskFileManager;
import kbot.managers.TaskManager;
import kbot.tasks.Deadline;
import kbot.tasks.Event;
import kbot.tasks.Task;
import kbot.tasks.ToDo;

/**
 * An add task command that adds a task onto the tasks array.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class AddTask extends Command {
    /** Name of command. */
    private String instruction;

    /** Input parameters to the command. */
    private String parameter;

    /** Output format for printing dates. */
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("d-M-yy");

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
     * @throws IOException           Exception for writing and loading from memory.
     * @throws InvalidInputException Exception when input parameters are invalid.
     */
    @Override
    public String execute() throws IOException, InvalidInputException {
        Task t = null;
        assert instruction != null && instruction.length() > 0 : "Command is not initialised or is empty.";
        String[] tagSplit = parameter.split("/tags", 2);
        String parametersWithoutTags = tagSplit[0].trim();
        ArrayList<String> tagsList = tagSplit.length > 1 ? handleTags(tagSplit[1]) : new ArrayList<String>();

        // create Command based on instruction
        try {
            switch (instruction) {
            case "todo":
                t = new ToDo(parametersWithoutTags, tagsList);
                break;
            case "deadline":
                t = makeDeadlineCommand(instruction, parametersWithoutTags, tagsList);
                break;
            case "event":
                t = makeEventCommand(instruction, parametersWithoutTags, tagsList);
                break;
            }
        } catch (DateTimeParseException e) {
            return ("Error while parsing date: Format should be d-M-yy.");
        } catch (InvalidInputException e) {
            return e.getMessage();
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


    /**
     * Handles the parsing of tags.
     *
     * @param parameter The String containing all the tags.
     * @return An array of Strings representing the tags in the Task.
     */
    public static ArrayList<String> handleTags(String parameter){
        ArrayList<String> tagsList = new ArrayList<>();
        if (parameter.trim() != "") {
            ParseTags parsedTags = new ParseTags(parameter);
            tagsList = parsedTags.tagsStringToArray();
        }
        return tagsList;
    }

    /**
     * Makes a new Deadline object.
     * 
     * @param instruction String representing the type of Task.
     * @param parameter   Parameters trailing the instruction.
     * @param tags        Tags user has input into the command line.
     * @return A new Deadline object.
     * @throws InvalidInputException  If the parameters are incorrect.
     * @throws DateTimeParseException If time format is not input correctly.
     */
    public static Deadline makeDeadlineCommand(String instruction, String parameter, ArrayList<String> tags)
            throws InvalidInputException, DateTimeParseException {
        System.out.println(parameter);
        String[] deadlineParameter = parameter.split(" /by ", 2);
        System.out.println(deadlineParameter[0] +" " + deadlineParameter[1]);
        if (deadlineParameter.length == 1) { // if there is command but no input
            throw new InvalidInputException(
                    "Invalid parameters for " + instruction + "\nType \"help\" if you're unsure.");
        }
        String deadlineName = deadlineParameter[0];
        String deadlineEndDate = deadlineParameter[1].trim();
        LocalDate deadline = LocalDate.parse(deadlineEndDate, PRINT_FORMAT);
        return new Deadline(deadlineName, deadline, tags);
    }

    /**
     * Makes a new Event object.
     * 
     * @param instruction String representing the type of Task.
     * @param parameter   Parameters trailing the instruction.
     * @param tags        Tags user has input into the command line.
     * @return A new Event object.
     * @throws InvalidInputException  If the parameters are incorrect.
     * @throws DateTimeParseException If time format is not input correctly.
     */
    public static Event makeEventCommand(String instruction, String parameter, ArrayList<String> tags)
            throws InvalidInputException, DateTimeParseException {
        String[] eventParameter = parameter.split(" /from ", 2);
        if (eventParameter.length == 1) { // if there is command but no input
            throw new InvalidInputException(
                    "Invalid parameters for " + instruction + "\nType \"help\" if you're unsure.");
        }
        String eventName = eventParameter[0];
        String eventDates = eventParameter[1];
        String[] dateParameters = eventDates.split(" /to ", 2);
        if (dateParameters.length < 2) { // if there are less than 2 dates given
            throw new InvalidInputException(
                    "Invalid parameters for " + instruction + "\nType \"help\" if you're unsure.");
        }
        String eventStartDate = dateParameters[0].trim();
        String eventEndDate = dateParameters[1].trim();
        LocalDate start = LocalDate.parse(eventStartDate, PRINT_FORMAT);
        LocalDate end = LocalDate.parse(eventEndDate, PRINT_FORMAT);
        return new Event(eventName, start, end, tags);
    }
}
