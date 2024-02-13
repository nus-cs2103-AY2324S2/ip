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
        Task task = null;
        int flag;
        int flag2;
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
            if (split.length != 2) {
                throw new MissingArgumentsExceptionMarking(split[0]);
            }
            try {
                Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("InvalidCommandException");
            }
            token = new Token(Command.DELETE, Integer.parseInt(split[1]));
            break;
        case "unmark":
            if (split.length != 2) {
                throw new MissingArgumentsExceptionMarking(split[0]);
            }
            try {
                Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("InvalidCommandException");
            }
            token = new Token(Command.UNMARK, Integer.parseInt(split[1]));
            break;
        case "mark":
            if (split.length != 2) {
                throw new MissingArgumentsExceptionMarking(split[0]);
            }
            try {
                Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("InvalidCommandException");
            }
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
            flag = Arrays.asList(split).indexOf("/from");
            flag2 = Arrays.asList(split).indexOf("/to");
            if (split.length < 5) {
                throw new MissingArgumentsExceptionEvents("event");
            } else if (flag < 2 || flag2 == split.length - 1 || flag2 - flag <= 1) {
                throw new MissingArgumentsExceptionEvents("event");
            }
            int spaceInEventCmd = this.input.indexOf(" ");
            int fromInEvenCmd = this.input.indexOf("/from");
            int toInEvenCmd = this.input.indexOf("/to");

            String fromDateTime;
            String toDateTime;

            // Processes From DateTime
            try {
                checkTimeFormat(this.input.substring(fromInEvenCmd + 5, toInEvenCmd).trim());

                String[] temporaryArray = this.input.substring(fromInEvenCmd + 5, toInEvenCmd)
                        .trim().split("[\\s/\\-]+");

                int lenTemp = temporaryArray.length;

                for (int i = 0; i < temporaryArray.length / 2; i++) {
                    String temp = temporaryArray[i];
                    temporaryArray[i] = temporaryArray[lenTemp - 1 - i];
                    temporaryArray[lenTemp - 1 - i] = temp;
                }

                if (temporaryArray[lenTemp - 2].length() == 1) {
                    temporaryArray[lenTemp - 2] = "0" + temporaryArray[lenTemp - 2];
                }

                if (temporaryArray[lenTemp - 1].length() == 1) {
                    temporaryArray[lenTemp - 1] = "0" + temporaryArray[lenTemp - 1];
                }

                fromDateTime = String.join("-", temporaryArray);

            } catch (WrongTimeFormatException exception) {
                throw exception;
            }
            // Processes To DateTime
            try {
                checkTimeFormat(this.input.substring(toInEvenCmd + 3).trim());

                String[] temporaryArray = this.input.substring(toInEvenCmd + 3)
                        .trim().split("[\\s/\\-]+");

                int lenTemp = temporaryArray.length;

                for (int i = 0; i < temporaryArray.length / 2; i++) {
                    String temp = temporaryArray[i];
                    temporaryArray[i] = temporaryArray[lenTemp - 1 - i];
                    temporaryArray[lenTemp - 1 - i] = temp;
                }

                if (temporaryArray[lenTemp - 1].length() == 1) {
                    temporaryArray[lenTemp - 1] = "0" + temporaryArray[lenTemp - 1];
                }

                if (temporaryArray[lenTemp - 2].length() == 1) {
                    temporaryArray[lenTemp - 2] = "0" + temporaryArray[lenTemp - 2];
                }

                toDateTime = String.join("-", temporaryArray);

            } catch (WrongTimeFormatException exception) {
                throw exception;
            }

            task = new Events(this.input, this.input.substring(spaceInEventCmd + 1, fromInEvenCmd)
                    .trim(), fromDateTime, toDateTime);
            token = new Token(Command.EVENT, task);
            break;
        case "deadline":
            flag = Arrays.asList(split).indexOf("/by");

            if (split.length < 4) {
                throw new MissingArgumentsExceptionDeadlines("deadline");
            } else if (flag < 2 || flag == split.length - 1) {
                throw new MissingArgumentsExceptionDeadlines("deadline");
            } else {
                int space = this.input.indexOf(" ");
                int by = this.input.indexOf("/by");
                String byDateTime;

                try {
                    checkTimeFormat(this.input.substring(by + 3).trim());

                    String[] temporaryArray = this.input.substring(by + 3)
                            .trim().split("[\\s/\\-]+");
                    int lenTemp = temporaryArray.length;

                    for (int i = 0; i < temporaryArray.length / 2; i++) {
                        String temp = temporaryArray[i];
                        temporaryArray[i] = temporaryArray[lenTemp - 1 - i];
                        temporaryArray[lenTemp - 1 - i] = temp;
                    }

                    if (temporaryArray[lenTemp - 1].length() == 1) {
                        temporaryArray[lenTemp - 1] = "0" + temporaryArray[lenTemp - 1];
                    }

                    if (temporaryArray[lenTemp - 2].length() == 1) {
                        temporaryArray[lenTemp - 2] = "0" + temporaryArray[lenTemp - 2];
                    }

                    byDateTime = String.join("-", temporaryArray);
                } catch (WrongTimeFormatException exception) {
                    throw exception;
                }

                task = new Deadlines(this.input, this.input.substring(space + 1, by).trim(), byDateTime);
                token = new Token(Command.DEADLINE, task);
            }
            break;
        case "save":
            boolean marked = split[1].equals("1") ? true : false;

            this.input = String.join(" ", Arrays.copyOfRange(split, 2, split.length));

            token = this.parse();

            if (marked) {
                token.getTask().mark();
            }

            token.setAsSaved();
            break;
        default:
            throw new InvalidCommandException("InvalidCommandException");
        }
        return token;
    }

    /**
     * Checks the format of the provided time string and throws an exception if the format is incorrect.
     *
     * @param string The time string to be checked.
     * @throws WrongTimeFormatException If the time format is incorrect or missing.
     */
    private void checkTimeFormat(String string) throws WrongTimeFormatException {
        String[] splitString = string.split("[\\s/\\-]+");

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

            checkRealDate(year, month, day);
        } catch (NumberFormatException e) {
            throw new WrongTimeFormatException("Use numerals for date");
        } catch (WrongTimeFormatException exception) {
            throw exception;
        }

        if (splitString.length > 3) {
            String twelveHourFormat = "";

            if (splitString.length > 4) {
                twelveHourFormat = splitString[4];
            }

            checkRealTime(splitString[3], twelveHourFormat);
        }
    }

    /**
     * Checks if the provided year, month, and day form a valid date and throws an exception if not.
     *
     * @param year  The year.
     * @param month The month.
     * @param day   The day.
     * @throws WrongTimeFormatException If the date is invalid.
     */
    private void checkRealDate(int year, int month, int day) throws WrongTimeFormatException {
        try {
            LocalDate dateToBeChecked = LocalDate.of(year, month, day);

            if (dateToBeChecked.isBefore(LocalDate.now())) {
                throw new WrongTimeFormatException("Can't go back in time buddy");
            }
        } catch (DateTimeException e) {
            throw new WrongTimeFormatException("Date and time are impossible buddy");
        } catch (WrongTimeFormatException exception) {
            throw exception;
        }
    }

    /**
     * Checks if the provided time and twelve-hour format (AM/PM) form a valid time and throws an exception if not.
     *
     * @param time             The time string.
     * @param twelveHourFormat The twelve-hour format (AM/PM).
     * @throws WrongTimeFormatException If the time is invalid.
     */
    private void checkRealTime(String time, String twelveHourFormat) throws WrongTimeFormatException {
        if (twelveHourFormat.equals("")) {
            if (time.length() < 5 && time.indexOf(":") != -1) {
                time = "0" + time;
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm]" + "[HHmm]" + "[Hmm]");
                LocalTime.parse(time, formatter);
            } catch (DateTimeException exception) {
                throw new WrongTimeFormatException("Date and time are impossible buddy");
            }
        } else if (twelveHourFormat.equals("PM") || twelveHourFormat.equals("pm") || twelveHourFormat.equals("Pm")
                || twelveHourFormat.equals("pM")) {
            try {
                LocalTime.parse(time + " pm", DateTimeFormatter.ofPattern("h:mm a"));
            } catch (DateTimeException exception) {
                throw new WrongTimeFormatException("Date and time are impossible buddy");
            }
        }
    }

}
