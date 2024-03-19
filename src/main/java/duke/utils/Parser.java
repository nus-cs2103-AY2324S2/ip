package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exceptions.EmptyBodyException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidKeyException;
import duke.exceptions.InvalidNumberException;
import duke.exceptions.WrongFormatException;

/**
 * Parser to read and understand user inputs. Stores the parsed information inside the object.
 */
public class Parser {
    public static final DukeDateFormater FORMATER = new DukeDateFormater();
    private KeyEnum currentKey;
    private String inputDetail;
    private LocalDate to;
    private LocalDate from;
    private Integer index;
    private String[] userInputSplit;

    /**
     * Parses user input and store the information.
     *
     * @param userInput String user input.
     * @throws InvalidKeyException    If the command keyword is unknown.
     * @throws EmptyBodyException     If the command body is empty.
     * @throws WrongFormatException   If the format of command is wrong.
     * @throws InvalidNumberException If the number in the command is not a number.
     */
    public void parse(String userInput) throws InvalidKeyException, EmptyBodyException,
            WrongFormatException, InvalidNumberException, DateTimeParseException, InvalidDateTimeException {
        String userInputLowerCase = userInput.toLowerCase();
        userInputSplit = userInputLowerCase.split(" ");
        // verify that the user input is valid
        isValidCommand();
        this.determineCurrentKey(userInputSplit[0]);
        switch (this.currentKey) {
        case DEADLINE:
            parseDeadlineCommand(userInputLowerCase);
            break;
        case TODO:
        case FIND:
            parseOneParameterCommand(userInputLowerCase);
            break;
        case EVENT:
            parseEventCommand(userInputLowerCase);
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            parseIndexCommand(userInputLowerCase);
            break;
        default:
            break;
        }

        // check if the user input contains only empty spaces.
        checkEmptyCommand(userInput);
    }

    /**
     * Checks if the user input is valid.
     */
    public void isValidCommand() {
        String checkInputMsg = checkSpecialCharacter(userInputSplit);
        if (!checkInputMsg.equals("valid")) {
            throw new WrongFormatException(checkInputMsg);
        }
    }

    /**
     * Parses deadline command and store the information.
     * @param userInput String user input.
     */
    public void parseDeadlineCommand(String userInput) {
        if (userInput.length() <= 9) {
            throw new EmptyBodyException();
        }
        try {
            inputDetail = userInput.substring(9, userInput.indexOf("/by"));
            to = formatDate(userInput.substring(userInput.indexOf("/by") + 4));
        } catch (Exception e) {
            throw new WrongFormatException("\"deadline content /by yyyy-mm-dd\"");
        }
    }

    /**
     * Parses command with one parameter and store the information.
     * @param userInput String user input.
     */
    public void parseOneParameterCommand(String userInput) {
        if (userInput.length() <= 5) {
            throw new EmptyBodyException();
        }
        inputDetail = userInput.substring(5);
    }

    /**
     * Parses event command and store the information.
     * @param userInput String user input.
     */
    public void parseEventCommand(String userInput) {
        if (userInput.length() <= 6) {
            throw new EmptyBodyException();
        }
        try {
            inputDetail = userInput.substring(6, userInput.indexOf("/from"));
            from = formatDate(userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1));
            to = formatDate(userInput.substring(userInput.indexOf("/to") + 4));
            if (from.isAfter(to)) {
                throw new InvalidDateTimeException("The start date should be before the end date");
            }
        } catch (InvalidDateTimeException e) {
            throw e;
        } catch (Exception e) {
            throw new WrongFormatException("\"event content /from yyyy-mm-dd /to yyyy-mm-dd\"");
        }
    }

    /**
     * Parses command with one index as parameter and store the information.
     * @param userInput String user input.
     */
    public void parseIndexCommand(String userInput) {
        try {
            this.index = Integer.parseInt(userInputSplit[1]) - 1;
            this.inputDetail = this.index.toString();
        } catch (Exception e) {
            throw new InvalidNumberException();
        }
    }

    /**
     * Checks if the user input contains no information.
     * @param userInput String user input.
     */
    public void checkEmptyCommand(String userInput) {
        if (!(currentKey.equals(KeyEnum.LIST) || currentKey.equals(KeyEnum.EXITKEY)) && inputDetail.trim().isEmpty()) {
            throw new EmptyBodyException();
        }
    }

    /**
     * Checks if the user input contains special character.
     *
     * @param userInput String user input.
     * @return True if the user input is valid.
     */
    public String checkSpecialCharacter(String[] userInput) {
        // match the special character |
        for (String s : userInput) {
            if (s.contains("|")) {
                return "Should not use special character |";
            }
        }
        return "valid";
    }

    /**
     * Maps userInputKey to keys defined in the KeyEnum class.
     *
     * @param userInputKey String user input key.
     */
    public void determineCurrentKey(String userInputKey) {
        switch (userInputKey) {
        case "bye":
            currentKey = KeyEnum.EXITKEY;
            break;
        case "list":
            currentKey = KeyEnum.LIST;
            break;
        case "mark":
            currentKey = KeyEnum.MARK;
            break;
        case "unmark":
            currentKey = KeyEnum.UNMARK;
            break;
        case "event":
            currentKey = KeyEnum.EVENT;
            break;
        case "deadline":
            currentKey = KeyEnum.DEADLINE;
            break;
        case "todo":
            currentKey = KeyEnum.TODO;
            break;
        case "delete":
            currentKey = KeyEnum.DELETE;
            break;
        case "find":
            currentKey = KeyEnum.FIND;
            break;
        default:
            currentKey = KeyEnum.INVALID;
            break;
        }
        assert currentKey != null : "currentKey should not be null";
        if (this.currentKey.equals(KeyEnum.INVALID)) {
            // raise InvalidKeyException
            throw new InvalidKeyException();
        }
    }

    /**
     * Changes date from string to LocalDate object.
     *
     * @param str date in string form
     * @return date in LocalDate form
     */
    public static LocalDate formatDate(String str) {
        LocalDate date = null;
        try {
            date = FORMATER.stringToDate(str);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
        assert date != null;
        return date;
    }

    public KeyEnum getCurrentKey() {
        return currentKey;
    }

    public String getInputDetail() {
        return inputDetail;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public Integer getIndex() {
        return index;
    }
}
