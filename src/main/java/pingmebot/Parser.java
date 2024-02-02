package pingmebot;

import pingmebot.task.ToDos;
import pingmebot.task.Deadline;
import pingmebot.task.Events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.ArrayList;

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
    public Parser (String userInput) {
        this.userInput = userInput;
        this.words = new ArrayList<>(Arrays.asList(userInput.split(" ")));
    }

    /**
     * Creates a ToDos object with a specified task description after parsing user's command with the ToDo command.
     *
     * @return A ToDos object with a specified task description.
     * @throws PingMeException If the user did not specify any task description or when the user command is not understood.
     */
    public ToDos parseToDoCommand() throws PingMeException {
        try {
            if (!this.words.get(1).isEmpty()) {
                StringBuilder description = new StringBuilder(this.words.get(1));
                for (int i = 2; i < words.size(); i++) {
                    description.append(" ").append(this.words.get(i));
                }
                return new ToDos(description.toString());

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("OOPS! The command is incomplete. Please provide a task description!");
        }
    }


    /**
     * Creates a Deadline object with a specified task description and a datetime to do the task by after parsing user's command with the Deadline command.
     *
     * @return A Deadline object with a specified task description and a datetime to compelte the task.
     * @throws PingMeException If the user did not specify any task description or datetime to finish the task, or when the user command is not understood.
     */
    public Deadline parseDeadlineCommand() throws PingMeException {
        StringBuilder description = new StringBuilder();
        StringBuilder by  = new StringBuilder();
        int index = this.words.indexOf("/by");
        if (index != -1) {
            if (index != 1) {
                description = new StringBuilder(this.words.get(1)); // if the user forgets to include description field
            }

        } else {
            throw new PingMeException("I don't understand your command. Try writing: deadline (task description) /by (d/m/yyyy HHmm format)");
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
                throw new PingMeException("I don't understand your command. Try writing: deadline (task description) /by (d/m/yyyy HHmm format)");
            }
            return new Deadline(description.toString(), parsedDateTime);

        } else {
            throw new PingMeException("You have missing fields! You need a task description & a deadline to finish your task, try again!");
        }
    }

    /**
     * Creates an Events object with a specified task description, start and end date or time of the event after parsing user's command with the Events command.
     *
     * @return An Events object with a specified task description, start and end date or time of the event.
     * @throws PingMeException If the user did not specify any task description, start and end date or time of the event or when the user command is not understood.
     */
    public Events parseEventsCommand() throws PingMeException {
        StringBuilder description = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        int indexOfFrom = this.words.indexOf("/from");
        int indexOfTo = this.words.indexOf("/to");

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new PingMeException("I don't understand your command. Try writing: event (task description) /from (date/time) /to (date/time)");

        } else {
            if (indexOfFrom == 1 || indexOfTo == 1) {} else {
                description = new StringBuilder(this.words.get(1));
            }
        }

        for (int i = 2; i < words.size(); i++) {
            if (i < indexOfFrom) {
                description.append(" ").append(words.get(i));

            } else if (i > indexOfFrom && i < indexOfTo) {
                start.append(" ").append(words.get(i));

            } else if (i > indexOfTo){
                end.append(" ").append(words.get(i));
            }
        }

        if (description.toString().isEmpty() || start.toString().isEmpty() || end.toString().isEmpty()) {
            throw new PingMeException("You having missing fields! You need a task description, start and end date/time for your task, try again!");

        } else {
            return new Events(description.toString(), start.toString(), end.toString());
        }
    }

    /**
     * Returns a 0-based index of the task to be marked after parsing the user's comnmand with the mark command.
     *
     * @param currentNumOfTask The total number of task currently in the tasklist.
     * @return A 0-based index of the task to be marked.
     * @throws PingMeException If the index of the task in the tasklist is not specified or when it is higher or lower than the actual total number of tasks currently.
     */
    public int parseMarkCommand(int currentNumOfTask) throws PingMeException {
        try {
            if (!this.words.get(1).isEmpty()) {
                if (Integer.parseInt(this.words.get(1)) > currentNumOfTask || Integer.parseInt(this.words.get(1)) <= 0) {
                    throw new PingMeException("You have currently " + currentNumOfTask + " tasks. You cannot mark task larger or smaller than this!");

                } else {
                    return Integer.parseInt(this.words.get(1)) - 1;
                }

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure which task you wish to mark. Please specify the task you wish to mark and try again!");
        }
    }

    /**
     * Returns a 0-based index of the task to be un-marked after parsing the user's comnmand with the unmark command.
     *
     * @param currentNumOfTask The total number of task currently in the tasklist.
     * @return A 0-based index of the task to be un-marked.
     * @throws PingMeException If the index of the task in the tasklist is not specified or when it is higher or lower than the actual total number of tasks currently.
     */
    public int parseUnmarkCommand(int currentNumOfTask) throws PingMeException {
        try {
            if (!this.words.get(1).isEmpty()) {
                if (Integer.parseInt(this.words.get(1)) > currentNumOfTask || Integer.parseInt(this.words.get(1)) <= 0) {
                    throw new PingMeException("You have currently " + currentNumOfTask + " tasks. You cannot un-mark task larger or smaller than this!");

                } else {
                    return Integer.parseInt(this.words.get(1)) - 1;
                }

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure which task you wish to un-mark. Please specify the task you wish to un-mark and try again!");
        }
    }

    /**
     * Returns a 0-based index of the task to be deleted after parsing the user's comnmand with the delete command.
     *
     * @param currentNumOfTask The total number of task currently in the tasklist.
     * @return A 0-based index of the task to be marked.
     * @throws PingMeException If the index of the task in the tasklist is not specified or when it is higher or lower than the actual total number of tasks currently.
     */
    public int parseDeleteCommand(int currentNumOfTask) throws PingMeException {
        try {
            if (!this.words.get(1).isEmpty()) {
                if (Integer.parseInt(this.words.get(1)) > currentNumOfTask || Integer.parseInt(this.words.get(1)) <= 0) {
                    throw new PingMeException("You have currently " + currentNumOfTask + " tasks. You cannot delete task larger or smaller than this!");

                } else {
                    return Integer.parseInt(this.words.get(1)) - 1;
                }

            } else {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure which task you wish to delete. Please specify the task you want to delete and try again!");
        }
    }

    /**
     * Returns the keyword of what the user is trying to find in his/her tasklist.
     *
     * @return Keyword specified by the user.
     * @throws PingMeException If the user forgets to input keyword to find.
     */
    public String parseFindCommand() throws PingMeException {
        try {
            if (!this.words.get(1).isEmpty()) {
                return this.words.get(1);
            } else {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new PingMeException("I'm not sure what you are trying to find. Please specify a keyword and try again!");
        }
    }
}
