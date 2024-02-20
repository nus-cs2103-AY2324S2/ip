package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingArgumentsException;
import duke.exceptions.MissingArgumentsExceptionDeadlines;
import duke.exceptions.MissingArgumentsExceptionEvents;
import duke.exceptions.MissingArgumentsExceptionMarking;
import duke.exceptions.MissingArgumentsExceptionTodo;
import duke.exceptions.WrongTimeFormatException;
import duke.storage.Deadlines;
import duke.storage.Events;
import duke.storage.Task;
import duke.storage.Todos;


/**
 * The Parser class is responsible for parsing user input and converting it into meaningful commands and tasks.
 * It handles various command types such as listing tasks, adding, deleting, marking, and parsing time information.
 * The class also checks the validity of user input and throws exceptions for invalid or missing information.
 */
public class Parser {
    private String input;

    /**
     * Constructs a Parser object with an empty input.
     */
    public Parser() {
        this.input = "";
    }

    /**
     * Constructs a Parser object with the specified input.
     *
     * @param input The input string to be parsed.
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Sets the input string for the parser.
     *
     * @param input The input string to be parsed.
     */
    public void feed(String input) {
        this.input = input;
    }

    /**
     * Parses the input string and returns a Token object representing the command and associated data.
     *
     * @return The Token object representing the parsed command and associated data.
     * @throws InvalidCommandException   If the command is invalid.
     * @throws MissingArgumentsException If required arguments are missing.
     * @throws WrongTimeFormatException  If there is an issue with the time format in the input.
     */
    public Token parse() throws InvalidCommandException, MissingArgumentsException, WrongTimeFormatException {
        String[] split = this.input.split(" ");
        Token token;
        Task task;

        switch (split[0]) {
        case "list":
            if (split.length != 1) {
                throw new InvalidCommandException("InvalidCommandException");
            }
            token = new Token(Command.LIST);
            break;

        case "bye":
            if (split.length != 1) {
                throw new InvalidCommandException("InvalidCommandException");
            }
            token = new Token(Command.BYE);
            break;

        case "delete":
            checkDeleteMarkUnMark(split);
            token = new Token(Command.DELETE, Integer.parseInt(split[1]));
            break;

        case "unmark":
            checkDeleteMarkUnMark(split);
            token = new Token(Command.UNMARK, Integer.parseInt(split[1]));
            break;

        case "mark":
            checkDeleteMarkUnMark(split);
            token = new Token(Command.MARK, Integer.parseInt(split[1]));
            break;

        case "find":
            if (split.length == 1) {
                throw new MissingArgumentsExceptionTodo("find");
            }
            int spaceInFindCmd = this.input.indexOf(" ");
            token = new Token(Command.FIND);
            token.setSearchKey(this.input.substring(spaceInFindCmd + 1));
            break;

        case "todo":
            if (split.length == 1) {
                throw new MissingArgumentsExceptionTodo("todo");
            }
            int spaceInTodoCmd = this.input.indexOf(" ");
            task = new Todos(this.input, this.input.substring(spaceInTodoCmd + 1));
            token = new Token(Command.TODO, task);
            break;

        case "event":
            token = handleEvent(split);
            break;

        case "deadline":
            token = handleDeadline(split);
            break;

        case "save":
            token = handleSave(split);
            break;
        case "priority":
            Priority p = checkPriority(split);
            token = new Token(Command.PRIORITY, Integer.parseInt(split[1]), p);
            break;
        default:
            throw new InvalidCommandException("InvalidCommandException");
        }
        return token;
    }

    private Token handleEvent(String[] split) throws MissingArgumentsException, WrongTimeFormatException {
        int positionOfFromInCmd = Arrays.asList(split).indexOf("/from");
        int positionOfToInCmd = Arrays.asList(split).indexOf("/to");
        boolean isPositionOfFromLessThanTwo = positionOfFromInCmd < 2;
        boolean isPositionOfToLast = positionOfToInCmd == split.length - 1;
        boolean isPositionOfFromAndToLessThanOne = positionOfToInCmd - positionOfFromInCmd <= 1;

        if (split.length < 5) {
            throw new MissingArgumentsExceptionEvents("event");
        } else if (isPositionOfFromLessThanTwo || isPositionOfToLast
                || isPositionOfFromAndToLessThanOne) {
            throw new MissingArgumentsExceptionEvents("event");
        }
        int spaceInEventCmd = this.input.indexOf(" ");
        int fromInEventCmd = this.input.indexOf("/from");
        int toInEvenCmd = this.input.indexOf("/to");

        String fromDateTime = checkEventFrom(fromInEventCmd, toInEvenCmd);
        String toDateTime = checkEventTo(toInEvenCmd);
        Task task = new Events(this.input, this.input.substring(spaceInEventCmd + 1, fromInEventCmd)
                .trim(), fromDateTime, toDateTime);
        return new Token(Command.EVENT, task);
    }

    private Token handleDeadline(String[] split) throws MissingArgumentsException, WrongTimeFormatException {
        int positionOfByInCmd = Arrays.asList(split).indexOf("/by");
        boolean isPositionOfByLessThanTwo = positionOfByInCmd < 2;
        boolean isPositionOfByLast = positionOfByInCmd == split.length - 1;
        if (split.length < 4) {
            throw new MissingArgumentsExceptionDeadlines("deadline");
        } else if (isPositionOfByLessThanTwo || isPositionOfByLast) {
            throw new MissingArgumentsExceptionDeadlines("deadline");
        }
        int spaceInDeadlineCmd = this.input.indexOf(" ");
        int byInDeadlineCmd = this.input.indexOf("/by");

        String byDateTime = checkDeadline();
        Task task = new Deadlines(this.input, this.input.substring(spaceInDeadlineCmd + 1, byInDeadlineCmd)
                .trim(), byDateTime);
        return new Token(Command.DEADLINE, task);
    }

    private Token handleSave(String[] split) throws InvalidCommandException, MissingArgumentsException,
            WrongTimeFormatException {
        boolean marked = split[1].equals("1") ? true : false;
        this.input = String.join(" ", Arrays.copyOfRange(split, 3, split.length));
        Token token = this.parse();
        if (marked) {
            token.getTask().mark();
        }
        switch (split[2]) {
        case "0":
            token.getTask().setPriority(Priority.NONE);
            break;
        case "1":
            token.getTask().setPriority(Priority.HIGH);
            break;
        case "-1":
            token.getTask().setPriority(Priority.LOW);
            break;
        default:
        }
        token.setAsSaved();
        return token;
    }

    private Priority checkPriority(String[] split) throws MissingArgumentsException,
            InvalidCommandException {
        if (split.length != 3) {
            throw new MissingArgumentsException("priority {index} {high/low}");
        }
        try {
            Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("InvalidCommandException");
        }

        if (split[2].toLowerCase().equals("high")) {
            return Priority.HIGH;
        } else if (split[2].toLowerCase().equals("low")) {
            return Priority.LOW;
        } else {
            throw new InvalidCommandException("InvalidCommandException");
        }
    }

    /**
     * Checks if the delete, unmark, or mark command has the correct number of arguments and if the task ID is valid.
     *
     * @param split The array of split input tokens.
     * @throws MissingArgumentsExceptionMarking If the number of arguments for marking a task is incorrect.
     * @throws InvalidCommandException          If the command is invalid or the task ID is not a number.
     */
    private void checkDeleteMarkUnMark(String[] split) throws MissingArgumentsExceptionMarking,
            InvalidCommandException {
        if (split.length != 2) {
            throw new MissingArgumentsExceptionMarking(split[0]);
        }
        try {
            Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("InvalidCommandException");
        }
    }


    /**
     * Checks the deadline command for the correct format and returns the formatted deadline date-time string.
     *
     * @return The formatted deadline date-time string.
     * @throws WrongTimeFormatException If the deadline format is incorrect or missing.
     */
    private String checkDeadline() throws WrongTimeFormatException {
        int by = this.input.indexOf("/by");

        try {
            String[] dateTime = checkDateTimeFormat(this.input.substring(by + 3).trim());
            return String.join("-", dateTime);

        } catch (WrongTimeFormatException exception) {
            throw exception;
        }
    }

    /**
     * Checks the event command's 'from' keyword for the correct format and returns the formatted 'from'
     * date-time string.
     *
     * @param fromInEventCmd The index of the 'from' keyword in the input command.
     * @param toInEventCmd   The index of the 'to' keyword in the input command.
     * @return The formatted 'from' date-time string.
     * @throws WrongTimeFormatException If the 'from' keyword format is incorrect or missing.
     */
    private String checkEventFrom(int fromInEventCmd, int toInEventCmd) throws WrongTimeFormatException {
        try {
            String[] dateTime = checkDateTimeFormat(this.input.substring(fromInEventCmd + 5, toInEventCmd).trim());
            return String.join("-", dateTime);

        } catch (WrongTimeFormatException exception) {
            throw exception;
        }
    }

    /**
     * Checks the event command's 'to' keyword for the correct format and returns the formatted 'to'
     * date-time string.
     *
     * @param toInEventCmd The index of the 'to' keyword in the input command.
     * @return The formatted 'to' date-time string.
     * @throws WrongTimeFormatException If the 'to' keyword format is incorrect or missing.
     */
    private String checkEventTo(int toInEventCmd) throws WrongTimeFormatException {
        try {
            String[] dateTime = checkDateTimeFormat(this.input.substring(toInEventCmd + 3).trim());
            return String.join("-", dateTime);

        } catch (WrongTimeFormatException exception) {
            throw exception;
        }

    }

    /**
     * Checks the format of the provided date and time string and returns a formatted date-time string.
     *
     * @param string The date and time string to be checked.
     * @return The formatted date-time string.
     * @throws WrongTimeFormatException If the date and time format is incorrect or missing.
     */
    private String[] checkDateTimeFormat(String string) throws WrongTimeFormatException {
        String[] splitString = string.split("[\\s/\\-]+");
        String[] dateTime = new String[2];

        if (splitString.length < 3) {
            throw new WrongTimeFormatException("wrong time buddy");
        }
        if (splitString.length > 5) {
            throw new WrongTimeFormatException("Too many inputs");
        }

        try {
            int year = Integer.parseInt(splitString[2]);
            int month = Integer.parseInt(splitString[1]);
            int day = Integer.parseInt(splitString[0]);

            dateTime[0] = checkRealDate(year, month, day);
        } catch (NumberFormatException e) {
            throw new WrongTimeFormatException("Use numerals for date");
        } catch (WrongTimeFormatException exception) {
            throw exception;
        }

        if (splitString.length > 4) {
            dateTime[1] = checkRealTime(splitString[3] + " " + splitString[4]);
        } else if (splitString.length > 3) {
            dateTime[1] = checkRealTime(splitString[3]);
        }

        return dateTime;
    }

    /**
     * Checks if the provided year, month, and day form a valid date and returns a formatted date string.
     *
     * @param year  The year.
     * @param month The month.
     * @param day   The day.
     * @return The formatted date string.
     * @throws WrongTimeFormatException If the date is invalid.
     */
    private String checkRealDate(int year, int month, int day) throws WrongTimeFormatException {
        try {
            LocalDate dateToBeChecked = LocalDate.of(year, month, day);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            if (dateToBeChecked.isBefore(LocalDate.now())) {
                throw new WrongTimeFormatException("Can't go back in time buddy");
            }
            return dateToBeChecked.format(dateFormatter);
        } catch (DateTimeException e) {
            throw new WrongTimeFormatException("Date and time are impossible buddy");
        } catch (WrongTimeFormatException exception) {
            throw exception;
        }
    }

    /**
     * Checks if the provided time and twelve-hour format (AM/PM) form a valid time and returns a formatted time string.
     *
     * @param time The time string.
     * @return The formatted time string.
     * @throws WrongTimeFormatException If the time is invalid.
     */
    private String checkRealTime(String time) throws WrongTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm]"
                    + "[HHmm]"
                    + "[Hmm]"
                    + "[h:mm a]"
                    + "[Hmm a]"
                    + "[H:mm]");
            DateTimeFormatter outPutFormatter = DateTimeFormatter.ofPattern("h:mm a");

            return LocalTime.parse(time, formatter).format(outPutFormatter);
        } catch (DateTimeException exception) {
            throw new WrongTimeFormatException("Date and time are impossible buddy");
        }
    }

}
