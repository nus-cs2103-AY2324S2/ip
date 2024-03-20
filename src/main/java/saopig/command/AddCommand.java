package saopig.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.TaskList;
import saopig.task.Todo;



/**
 * Represents a command to add a task to the task list.
 * A <code>AddCommand</code> object corresponds to
 * an executable command to add a task to the task list.
 * It contains the task description and the type of task to be added.
 * e.g., <code>todo read book</code>, <code>deadline return book /by 2020-02-02 18:00</code>,
 */
public class AddCommand extends Command {
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private String command;
    private int typeIndex; //0 for todo, 1 for deadline, 2 for event

    /**
     * Constructs a <code>AddCommand</code> object with the given command and type index.
     *
     * @param command   The command to be executed.
     * @param typeIndex The type of task to be added.
     */
    public AddCommand(String command, int typeIndex) {
        assert typeIndex == 0 || typeIndex == 1 || typeIndex == 2 : "Invalid type index";
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    /**
     * Adds a todo task to the task list.
     * The task description is extracted from the command.
     * The task is then added to the task list.
     * The task list is then saved to the hard disk.
     * The user interface prints a message to the user to indicate the task has been added.
     * If the command is invalid, the user interface prints a message to the user to indicate the error.
     * e.g., <code>todo read book</code>
     *
     * @param input     The command to be executed.
     * @param taskList  The task list to be added to.
     * @param ui        The user interface to be used.
     * @param storage   The storage to be used.
     */
    public String addTodoTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            assert input.length() >= 6 : "Input length should be at least 6";
            String processedInput = input.substring(5);
            Todo task = new Todo(processedInput);
            taskList.addTodoTask(task);
            storage.saveTaskList(taskList);
            return ("\n"
                    + "Oh, splendid! Your Todo task: {" + task.toString() + "} has been added successfully.\n "
                    + "Now you have " + taskList.getTasks().size() + " tasks in the list.");
        } catch (SaopigInvaildSizeException e) {
            return (e.getMessage() + ui.getTodoMissDetailMessage());
        }
    }

    /**
     * Adds a deadline task to the task list.
     * The task description and deadline time are extracted from the command.
     * The task is then added to the task list.
     * The task list is then saved to the hard disk.
     * The user interface prints a message to the user to indicate the task has been added.
     * If the command is invalid, the user interface prints a message to the user to indicate the error.
     * e.g., <code>deadline return book /by 2020-02-02 18:00</code>
     *
     * @param input     The command to be executed.
     * @param taskList  The task list to be added to.
     * @param ui        The user interface to be used.
     * @param storage   The storage to be used.
     */
    public String addDeadlineTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            StringBuilder response = new StringBuilder();
            checkValue(input.length(), 10, Integer.MAX_VALUE);
            assert input.length() >= 10 : "Input length should be at least 10";
            String splitInput = input.substring(9);
            String[] splitArguments = splitInput.split(" /by ");
            if (deadlineLengthChecker(splitArguments, response)) {
                return response.toString();
            }
            LocalDateTime deadlineDateTime = null;
            deadlineDateTime = getDeadlineDateTime(deadlineDateTime, splitArguments, response);
            Deadline task = new Deadline(splitArguments[0], deadlineDateTime);
            taskList.addDeadlineTask(task);
            storage.saveTaskList(taskList);
            addDeadlineMessage(taskList, response, task);
            return response.toString();
        } catch (SaopigInvaildSizeException e) {
            return (e.getMessage() + ui.getDeadlineMissDetailMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            return (ui.getDeadlineMissTimeMessage());
        }
    }

    private static LocalDateTime getDeadlineDateTime(
            LocalDateTime deadlineDateTime, String[] splitArguments, StringBuilder response) {
        try {
            deadlineDateTime = LocalDateTime.parse(splitArguments[1], DATE_TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            invalidDateTimeMessage(response);
        }
        return deadlineDateTime;
    }

    private static boolean deadlineLengthChecker(String[] splitArguments, StringBuilder response) {
        if (splitArguments.length != 2) {
            response.append("\n"
                    + "Whoopsie!\n "
                    + "It seems like you may have forgotten to write the deadline time.");
            return true;
        }
        return false;
    }

    private static void invalidDateTimeMessage(StringBuilder response) {
        response.append("\n"
                + "Whoopsie!\n "
                + "It seems like you may have given an invalid date time format.\n "
                + "Please use the format: yyyy-MM-dd HH:mm");
    }

    private void addDeadlineMessage(TaskList taskList, StringBuilder response, Deadline task) {
        response.append("\n" + "Oh, splendid! Your Deadline task: {")
                .append(task.toString())
                .append("} has been added successfully.\n ")
                .append("Now you have ").append(taskList.getTasks().size())
                .append(" tasks in the list.");
    }

    /**
     * Adds an event task to the task list.
     * The task description and event start and end time are extracted from the command.
     * The task is then added to the task list.
     * The task list is then saved to the hard disk.
     * The user interface prints a message to the user to indicate the task has been added.
     * If the command is invalid, the user interface prints a message to the user to indicate the error.
     * e.g., <code>event project meeting /from 2020-02-02 18:00 /to 2020-02-02 20:00</code>
     *
     * @param input     The command to be executed.
     * @param taskList  The task list to be added to.
     * @param ui        The user interface to be used.
     * @param storage   The storage to be used.
     */
    public String addEventTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            StringBuilder response = new StringBuilder();
            checkValue(input.length(), 7, Integer.MAX_VALUE);
            assert input.length() >= 7 : "Input length should be at least 7";
            String splitInput = input.substring(6);
            String[] splitArguments = splitInput.split("/");
            if (eventLengthChecker(splitArguments, response)) {
                return response.toString();
            }
            assert splitArguments.length == 3 : "Split arguments length should be 3";
            String description = splitArguments[0].trim();
            String fromTime = splitArguments[1].trim().substring(5);
            String toTime = splitArguments[2].trim().substring(3);
            LocalDateTime fromDateTime = null;
            LocalDateTime toDateTime = null;
            fromDateTime = getEventDateTime(fromDateTime, fromTime, response);
            toDateTime = getEventDateTime(toDateTime, toTime, response);
            Event task = new Event(description, fromDateTime, toDateTime);
            taskList.addEventTask(task);
            storage.saveTaskList(taskList);
            addEventMessage(taskList, response, task);
            return response.toString();
        } catch (SaopigInvaildSizeException e) {
            return (e.getMessage() + ui.getEventMissDetailMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            return (ui.getEventMissTimeMessage());
        }
    }

    private static LocalDateTime getEventDateTime(
            LocalDateTime dateTime, String time, StringBuilder response) {
        try {
            dateTime = LocalDateTime.parse(time, DATE_TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            invalidDateTimeMessage(response);
        }
        return dateTime;
    }

    private static boolean eventLengthChecker(String[] splitArguments, StringBuilder response) {
        if (splitArguments.length != 3) {
            response.append("\n"
                    + "Whoopsie!\n "
                    + "It seems like you may have forgotten to write the event start or end time ");
            return true;
        }
        return false;
    }

    private static void addEventMessage(TaskList taskList, StringBuilder response, Event task) {
        response.append("\n" + "Oh, splendid! " + "Your Event task: {")
                .append(task.toString())
                .append("} has been added successfully.\n ")
                .append("Isn't it just wonderful when things go exactly as planned?\n ")
                .append("I'm so proud of you for getting it done!\n ")
                .append("Now you have ")
                .append(taskList.getTasks().size())
                .append(" tasks in the list.");
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param tasks   The task list to be added to.
     * @param ui      The user interface to be used.
     * @param storage The storage to be used.
     * @return Response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "";
        switch (typeIndex) {
        case 0:
            response = addTodoTask(command, tasks, ui, storage);
            break;
        case 1:
            response = addDeadlineTask(command, tasks, ui, storage);
            break;
        case 2:
            response = addEventTask(command, tasks, ui, storage);
            break;
        default:
            break;
        }
        return response;
    }

    /**
     * Returns false to indicate the program should continue running.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
