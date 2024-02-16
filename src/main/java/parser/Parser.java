package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import task.ActionTask;
import task.ActionTask.ActionType;
import task.Deadline;
import task.Event;
import task.IncorrectTask;
import task.Task;
import task.ToDo;
import util.Messages;

/**
 * The parser class is responsible for interpreting and converting
 * user input into executionable actions in the main application.
 *
 * This class supports parsing descriptions that can either represent
 * commands (like list, mark task, etc). Corresponding to the input, the
 * parser produces a result that encapsulates the intended action.
**/
public class Parser {

    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the provided user input and returns an ParseExecutionable
     * that represents either a command to be executed.
     * <p>
     * The method analyzes the input to determine its command type.
     * Unrecognized inputs are handled, returning a UNRECOGNIZED ActionTask.
     * The other recognized inputs are handled with their corresponding actions.
     * </p>
     *
     * @param userInput the user input to be parsed.
     * @return an instance of ParseExecutionable to encapsulate the necessary action.
     */
    public ParseExecutionable parseInput(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        String commandType = userInputSplit[0];
        switch (commandType) {
        case ToDo.TASK_TYPE:
            return this.createToDo(userInput);
        case Deadline.TASK_TYPE:
            return this.createDeadlines(userInput);
        case Event.TASK_TYPE:
            return this.createEvents(userInput);
        case ActionTask.LIST_TYPE:
            return new ActionTask(ActionType.LIST);
        case ActionTask.MARK_TYPE:
            return this.createMarkAction(userInput);
        case ActionTask.UNMARK_TYPE:
            return this.createUnmarkAction(userInput);
        case ActionTask.DELETE_TYPE:
            return this.createDeleteAction(userInput);
        case ActionTask.FIND_TYPE:
            return this.createSearchAction(userInput);
        case ActionTask.BYE_TYPE:
            return new ActionTask(ActionType.BYE);
        default:
            return new ActionTask(ActionType.UNRECOGNIZED);
        }
    }

    /**
     * Parses the user input into a ToDo Task.
     *
     * @param userInput user's input in the main application.
     * @return the ParseExecutionable that when excuted, creates a ToDo object.
     */
    public ParseExecutionable createToDo(String userInput) {
        String[] taskName = userInput.split("todo ");
        if (taskName.length != 2) {
            return new IncorrectTask(Messages.MESSAGE_WRONG_PARAMETERS);
        }
        Task t = new ToDo(taskName[1]);
        return t;
    }

    /**
     * Parses the user input into a Deadline Task.
     *
     * @param userInput user's input in the main application.
     * @return the ParseExecutionable that when excuted, creates a Deadline object.
     */
    public ParseExecutionable createDeadlines(String userInput) {
        String[] newSplit = userInput.split("/");
        String[] taskName = newSplit[0].split("deadline ");
        String dateString = userInput.split("by ")[1].trim();
        LocalDateTime dateTime = parseDate(dateString);
        if (newSplit.length != 2 || (newSplit[1].split("by ").length != 2)) {
            return new IncorrectTask(Messages.MESSAGE_WRONG_PARAMETERS);
        }
        return new Deadline(taskName[1], dateTime);
    }

    /**
     * Parses the user input into a Event Task.
     *
     * @param userInput user's input in the main application.
     * @return the ParseExecutionable that when excuted, creates a Event object.
     */
    public ParseExecutionable createEvents(String userInput) {
        String[] newSplit = userInput.split("/");
        String[] taskName = newSplit[0].split("event ");

        int fromLength = newSplit[1].split("from ").length;
        int toLength = newSplit[2].split("to ").length;
        if (newSplit.length != 3 || fromLength != 2 || toLength != 2) {
            return new IncorrectTask(Messages.MESSAGE_WRONG_PARAMETERS);
        }
        return new Event(taskName[1], newSplit[1], newSplit[2]);
    }

    /**
     * Parses the user input into a search-type action
     *
     * @param userInput user's input in the main application.
     * @return the ParseExecutionable that when excuted, returns the Tasks that match.
     */
    public ParseExecutionable createSearchAction(String userInput) {
        String[] inputSplit = userInput.split(" ");
        if (inputSplit.length < 2) {
            return new IncorrectTask(Messages.MESSAGE_WRONG_PARAMETERS);
        }

        String searchKeyWord = "";
        for (int i = 1; i < inputSplit.length; i++) {
            searchKeyWord += inputSplit[i];
        }

        ActionTask t = new ActionTask(ActionType.FIND, searchKeyWord);
        return t;
    }

    /**
     * Parses the user input into a mark action
     *
     * @param userInput user's input in the main application.
     * @return the ParseExecutionable that when excuted, marks the specified object.
     */
    public ParseExecutionable createMarkAction(String userInput) {
        String[] inputSplit = userInput.split(" ");
        if (inputSplit.length != 2) {
            return new IncorrectTask(Messages.MESSAGE_WRONG_PARAMETERS);
        }
        int commandId = Integer.parseInt(inputSplit[1]);
        ActionTask t = new ActionTask(ActionType.MARK, commandId);
        return t;
    }

    /**
     * Parses the user input into a unmark action
     *
     * @param userInput user's input in the main application.
     * @return the ParseExecutionable that when excuted, unmarks the specified object.
     */
    public ParseExecutionable createUnmarkAction(String userInput) {
        String[] inputSplit = userInput.split(" ");
        if (inputSplit.length != 2) {
            return new IncorrectTask(Messages.MESSAGE_WRONG_PARAMETERS);
        }
        int commandId = Integer.parseInt(inputSplit[1]);
        return new ActionTask(ActionType.UNMARK, commandId);
    }

    /**
     * Parses the user input into a delete action
     * @param userInput user's input in the main application.
     * @return the ParseExecutionable that when excuted, deletes the specified object.
     */
    public ParseExecutionable createDeleteAction(String userInput) {
        String[] inputSplit = userInput.split(" ");
        if (inputSplit.length != 2) {
            return new IncorrectTask(Messages.MESSAGE_WRONG_PARAMETERS);
        }
        int commandId = Integer.parseInt(inputSplit[1]);
        return new ActionTask(ActionType.DELETE, commandId);
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param dateString String to be converted.
     * @return the corrsponding LocalDateTime object created.
     */
    public LocalDateTime parseDate(String dateString) {
        return LocalDateTime.parse(dateString, DATE_FORMAT);
    }
}
