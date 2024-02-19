package pingmebot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import pingmebot.command.*;
import pingmebot.task.Deadline;
import pingmebot.task.Events;
import pingmebot.task.ToDos;





/**
 * Helps to parse and make sense of user's inputs.
 */
public class Parser {
    protected String userInput;
    protected ArrayList<String> words;

    /**
     * Creates a parser class with user's inputs.
     * It also helps to break down user's commands into an array for better processing afterwards.
     *
     * @param userInput User's inputs.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
        this.words = new ArrayList<>(Arrays.asList(userInput.split(" ")));
    }

    /**
     * Creates an AddCommand object with a task with specified task description.
     *
     * @return A AddCommand object with a task with specified task description.
     * @throws PingMeException If the user did not specify any task description or
     *                         when the user command is not understood.
     */
    public AddCommand parseToDoCommand() throws PingMeException {
        try {
            if (!words.get(1).isEmpty()) {
                StringBuilder description = new StringBuilder(words.get(1));
                for (int i = 2; i < words.size(); i++) {
                    description.append(" ").append(words.get(i));
                }
                return new AddCommand(new ToDos(description.toString()));

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("OOPS! The command is incomplete. Please provide a task description!");
        }
    }


    /**
     * Creates an AddCommand object a Deadline object with  specified task description and a datetime to do the task by.
     *
     * @return An AddCommand object with a specified Deadline object.
     * @throws PingMeException If the user did not specify any task description or datetime to finish the task,
     *                         or when the user command is not understood.
     */
    public AddCommand parseDeadlineCommand() throws PingMeException {
        StringBuilder description = new StringBuilder();
        StringBuilder by = new StringBuilder();
        int index = words.indexOf("/by");
        if (index != -1) {
            if (index != 1) {
                description = new StringBuilder(words.get(1)); // if the user forgets to include description field
            }

        } else {
            throw new PingMeException("I don't understand your command. "
                    + "Try writing: deadline (task description) /by (d/m/yyyy HHmm format)");
        }

        for (int i = 2; i < words.size(); i++) {
            if (i < index) {
                description.append(" ").append(words.get(i));
            } else if (i > index) {
                by.append(" ").append(words.get(i));
            }
        }

        if (!(by.toString().isEmpty() || description.toString().isEmpty())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime parsedDateTime;

            try {
                parsedDateTime = LocalDateTime.parse(by.toString().trim(), formatter);
            } catch (DateTimeParseException e) {
                throw new PingMeException("I don't understand your command. "
                        + "Try writing: deadline (task description) /by (d/m/yyyy HHmm format)");
            }
            return new AddCommand(new Deadline(description.toString(), parsedDateTime));

        } else {
            throw new PingMeException("You have missing fields! "
                    + "You need a task description & a deadline to finish your task, try again!");
        }
    }

    /**
     * Creates an AddCommand object with an Events with a task description, start and end date or time of the event.
     *
     * @return An AddCommand object with a specified Events object.
     * @throws PingMeException If the user did not specify any task description,
     *                         start and end date or time of the event or when the user command is not understood.
     */
    public AddCommand parseEventsCommand() throws PingMeException {
        StringBuilder description = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        int indexOfFrom = words.indexOf("/from");
        int indexOfTo = words.indexOf("/to");

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new PingMeException("I don't understand your command. "
                    + "Try writing: event (task description) /from (date/time) /to (date/time)");

        } else {
            if (indexOfFrom == 1 || indexOfTo == 1) { } else {
                description = new StringBuilder(words.get(1));
            }
        }

        for (int i = 2; i < words.size(); i++) {
            if (i < indexOfFrom) {
                description.append(" ").append(words.get(i));

            } else if (i > indexOfFrom && i < indexOfTo) {
                start.append(" ").append(words.get(i));

            } else if (i > indexOfTo) {
                end.append(" ").append(words.get(i));
            }
        }

        if (description.toString().isEmpty() || start.toString().isEmpty()
                || end.toString().isEmpty()) {
            throw new PingMeException("You having missing fields! "
                    + "You need a task description, start and end date/time for your task, try again!");

        } else {
            return new AddCommand(new Events(description.toString(), start.toString(), end.toString()));
        }
    }

    /**
     * Returns a MarkCommand object with the 0-based index of the task to be marked.
     *
     * @param currentNumOfTask The total number of task currently in the tasklist.
     * @return A MarkCommand object with the 0-based index of the task to be marked.
     * @throws PingMeException If the index of the task in the tasklist is not specified or when it is higher or
     *                         lower than the actual total number of tasks currently.
     */
    public MarkCommand parseMarkCommand(int currentNumOfTask) throws PingMeException {
        try {
            if (!words.get(1).isEmpty()) {
                if (Integer.parseInt(words.get(1)) > currentNumOfTask
                        || Integer.parseInt(words.get(1)) <= 0) {
                    throw new PingMeException("You have currently " + currentNumOfTask + " tasks. "
                            + "You cannot mark task larger or smaller than this!");

                } else {
                    return new MarkCommand(Integer.parseInt(words.get(1)) - 1);
                }

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure which task you wish to mark. "
                    + "Please specify the task you wish to mark and try again!");
        }
    }

    /**
     * Returns a UnmarkCommand object with the 0-based index of the task to be un-marked.
     *
     * @param currentNumOfTask The total number of task currently in the tasklist.
     * @return A UnmarkCommand object with the 0-based index of the task to be un-marked.
     * @throws PingMeException If the index of the task in the tasklist is not specified or when it is higher or
     *                         lower than the actual total number of tasks currently.
     */
    public UnmarkCommand parseUnmarkCommand(int currentNumOfTask) throws PingMeException {
        try {
            if (!words.get(1).isEmpty()) {
                if (Integer.parseInt(words.get(1)) > currentNumOfTask
                        || Integer.parseInt(words.get(1)) <= 0) {
                    throw new PingMeException("You have currently " + currentNumOfTask + " tasks. "
                            + "You cannot un-mark task larger or smaller than this!");

                } else {
                    return new UnmarkCommand(Integer.parseInt(words.get(1)) - 1);
                }

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure which task you wish to un-mark. "
                    + "Please specify the task you wish to un-mark and try again!");
        }
    }

    /**
     * Returns a DeleteCommand with a 0-based index of the task to be deleted.
     *
     * @param currentNumOfTask The total number of task currently in the tasklist.
     * @return A DeleteCommand object with a 0-based index of the task to be marked.
     * @throws PingMeException If the index of the task in the tasklist is not specified or when it is higher or
     *                         lower than the actual total number of tasks currently.
     */
    public DeleteCommand parseDeleteCommand(int currentNumOfTask) throws PingMeException {
        try {
            if (!words.get(1).isEmpty()) {
                if (Integer.parseInt(words.get(1)) > currentNumOfTask
                        || Integer.parseInt(words.get(1)) <= 0) {
                    throw new PingMeException("You have currently " + currentNumOfTask + " tasks. "
                            + "You cannot delete task larger or smaller than this!");

                } else {
                    return new DeleteCommand(Integer.parseInt(words.get(1)) - 1);
                }

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure which task you wish to delete. Please specify the task "
                    + "you want to delete and try again!");
        }
    }

    /**
     * Returns the FindCommand object with the keyword of what the user is trying to find in his/her tasklist.
     *
     * @return FindCommand object with the keyword specified by the user.
     * @throws PingMeException If the user forgets to input keyword to find.
     */
    public FindCommand parseFindCommand() throws PingMeException {
        try {
            if (!words.get(1).isEmpty()) {
                return new FindCommand(words.get(1));
            } else {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure what you are trying to find. "
                    + "Please specify a keyword and try again!");
        }
    }

    /**
     * Returns the PostponeCommand object with the task which the user is trying to postpone.
     *
     * @return PostponeCommand object with the task number in the task list which the
     *         user is trying to postpone, the new start and end timing for an events task,
     *         and a finsih by timing for the deadline task.
     * @throws PingMeException If the user never includes the task to postpone, or a new start or end time.
     */

    public PostponeCommand parsePostponeCommand() throws PingMeException {
        try {
            if (words.get(1).isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("You have to select a task" +
                    " to postpone! You cannot select a todo task though :)");
        }
        int taskToPostpone = Integer.parseInt(words.get(1));
        int indexOfBy = words.indexOf("/by");
        int indexOfFrom = words.indexOf("/from");
        int indexOfTo = words.indexOf("/to");

        if (indexOfFrom != -1) {
            if (indexOfTo == -1) {
                throw new PingMeException("Make sure to include " +
                        "/from ___ /to ___ for events object");
            }
        } else if (indexOfBy == -1) {
            throw new PingMeException("Make sure to include /by ___ for deadline object" +
                    "and /from ___ /to ___ for events object");
        }

        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        StringBuilder by = new StringBuilder();

        if (words.size() < 6) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            for (int i = 2; i < words.size(); i++) {
                if (i > indexOfBy) {
                    by.append(" ").append(words.get(i));
                }
            }

            return new PostponeCommand(taskToPostpone - 1, " ", " ",
                    LocalDateTime.parse(by.toString().trim(), formatter));
        }

        if (words.size() > 5) {
            System.out.println("im here");
            for (int i = 2; i < words.size(); i++) {
                if (i > indexOfFrom && i < indexOfTo) {
                    from.append(words.get(i));
                } else if (i > indexOfTo) {
                    to.append(words.get(i));
                }
            }

            return new PostponeCommand(taskToPostpone - 1, from.toString(), to.toString(), null);
        }

        throw new PingMeException("Try again with your command!");
    }
}
