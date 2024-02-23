package chingu;

import chingu.command.AddCommand;
import chingu.command.Command;
import chingu.command.ListCommand;
import chingu.command.EditCommand;
import chingu.command.DeleteCommand;
import chingu.command.FindCommand;
import chingu.command.ExitCommand;
import chingu.exception.DateException;
import chingu.exception.DeadlineException;
import chingu.exception.NoCommandException;
import chingu.exception.ToDosException;
import chingu.exception.EventException;
import chingu.task.Task;
import chingu.task.Event;
import chingu.task.Deadline;
import chingu.task.ToDo;

/**
 * The class Parser deals with understanding and recording the user command.
 */

public class Parser {
    /**
     * Returns Command that is sorted by the function
     *
     * @param fullCommand full command by the user in String
     * @return Command
     * @throws NoCommandException
     */
    public static Command parse(String fullCommand) throws NoCommandException {
        String [] uCmd = fullCommand.split(" ", 2);
        if (uCmd.length < 2) {
            return parseOneWordCommand(fullCommand.toLowerCase());
        } else {
            String firstWord = uCmd[0].trim().toLowerCase();
            String furtherDetails = uCmd[1];
            return parseLongCommand(firstWord, furtherDetails);
        }
    }

    /**
     * Sorts the 1-word Command from the user
     *
     * @param fullCommand from the user
     * @return Command that is constructed based on User's command
     * @throws NoCommandException if no command is present or weird command is given
     */
    public static Command parseOneWordCommand(String fullCommand) throws NoCommandException {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new NoCommandException("Friend, please tell me what you want me to do.");
        }
    }

    /**
     * Sorts the more than 1-word Command from the user
     *
     * @param firstWord Main Command
     * @param furtherDetails Description
     * @return Command that is constructed based on User's command
     * @throws NoCommandException if no command is present or weird command is given
     */
    public static Command parseLongCommand(String firstWord, String furtherDetails) throws NoCommandException {
        assert !firstWord.equals("todos") : "You want to add 1 task at a time - todo";
        assert !firstWord.equals("events") : "You want to add 1 task at a time - event";
        assert !firstWord.equals("deadlines") : "You want to add 1 task at a time - deadline";
        switch (firstWord) {
            case "todo":
            case "event":
            case "deadline":
                return new AddCommand(firstWord, furtherDetails);
            case "find":
                return new FindCommand(furtherDetails);
            case "mark":
            case "unmark":
                return new EditCommand(firstWord, furtherDetails);
            case "delete":
                return new DeleteCommand(furtherDetails);
            default:
                throw new NoCommandException("Please tell me what you want me to do.");
        }
    }

    /**
     * Parse Command that adds ToDo
     *
     * @param description about ToDo
     * @return ToDo Task that is created
     * @throws ToDosException if necessary details are missing from the ToDo
     */
    public static Task parseToDos(String description) throws ToDosException {
        if (description.isEmpty()) {
            throw new ToDosException("What todos do you need to record?");
        }
        String descriptionPriority = description;
        String [] divided = descriptionPriority.split("/priority", 2);
        if (divided.length < 2) {
            throw new ToDosException("Please insert the priority of the task - /priority High/Mid/low");
        }
        description = divided[0];
        String priority = divided[1].trim();
        return new ToDo(description, priority);
    }

    /**
     * Parse Command that adds Deadline
     *
     * @param description about Deadline
     * @return Deadline that is created
     * @throws DeadlineException if necessary details are missing from the Deadline
     * @throws DateException if format of the date given is wrong
     */
    public static Task parseDeadline(String description) throws DeadlineException, DateException{
        if (description.isEmpty()) {
            throw new DeadlineException("What deadline do you need to record?");
        }
        String [] divided = description.split("/by", 2);
        checkDeadlineSplit(divided.length);
        String D = divided[0];
        String byPriority = divided[1].trim();
        divided = byPriority.split("/priority", 2);
        String by = divided[0].trim();
        String priority = divided[1].trim();
        if (priority.isEmpty()) {
            throw new DeadlineException("Please insert the priority of the task - /priority High/Mid/low");
        }
        // Solution (DateTime Formatting checks) was inspired by https://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
        boolean validateDate = by.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}");
        if (!validateDate) {
            throw new DateException("Please make sure that your date is in form of YYYY/MM/DD.");
        }
        return new Deadline(D, by, priority);
    }

    /**
     * Parse Command taht adds Event
     *
     * @param description about Event
     * @return Event that is created
     * @throws EventException if necessary details are missing from the Event
     */
    public static Task parseEvent(String description) throws EventException {
        if (description.isEmpty()) {
            throw new EventException("What event do you need to record?");
        }
        String [] divided = description.split("/from", 2);
        checkEventSplit(divided.length);
        String D = divided[0];
        String fromTo = divided[1];
        divided = fromTo.split("/to", 2);
        checkEventSplit(divided.length);
        String from = divided[0].trim();
        String toPriority = divided[1].trim();
        divided = toPriority.split("/priority", 2);
        checkEventSplit(divided.length);
        String to = divided[0].trim();
        String priority = divided[1].trim();
        validateDates(from, to);
        return new Event(D, from, to, priority);
    }

    /**
     * Check if the description is splitted correctly
     *
     * @param length number of splitted descriptions
     * @throws DeadlineException if necessary details are missing from the Deadline
     */
    public static void checkDeadlineSplit(int length) throws DeadlineException {
        if (length < 2) {
            throw new DeadlineException("Please insert the priority of the task - /priority High/Mid/low");
        }
    }

    /**
     * Check if the description is splitted correctly
     *
     * @param length number of splitted descriptions
     * @throws EventException if necessary details are missing from the Event
     */
    public static void checkEventSplit(int length) throws EventException{
        if (length < 2) {
            throw new EventException("There is no event timeline!");
        }
    }

    /**
     * Validate if the format of the event dates are correctly added by the user
     *
     * @param from - start date of the event
     * @param to - end date of the event
     */
    public static void validateDates(String from, String to) {
        //Solution (DateTime Formatting checks) was inspired by https://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
        boolean validateFromDate = from.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}\\[0-9]{4}");
        assert !validateFromDate : "format of the event start date is WRONG!";
        boolean validateToDate = to.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}\\[0-9]{4}");
        assert !validateToDate : "format of the event end date is WRONG!";
    }

}



