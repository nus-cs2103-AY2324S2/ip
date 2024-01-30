package duke.utils;

import duke.exceptions.EmptyBodyException;
import duke.exceptions.InvalidKeyException;
import duke.exceptions.WrongFormatException;

public class Parser {
    private KeyEnum currentKey = KeyEnum.INVALID;
    private String inputDetail;
    private String to;
    private String from;
    private Integer index;

    public void parse(String userInput) throws InvalidKeyException, EmptyBodyException, WrongFormatException {
        String[] userInputSplit = userInput.split(" ");
        this.determineCurrentKey(userInputSplit[0]);
        switch (this.currentKey) {
            case DEADLINE:
                if (userInput.length() <= 9) {
                    throw new EmptyBodyException();
                }
                try {
                    inputDetail = userInput.substring(9, userInput.indexOf("/by"));
                    to = userInput.substring(userInput.indexOf("/by")+4);
                } catch (Exception e) {
                    throw new WrongFormatException("\"deadline content /by yyyy-mm-dd\"");
                }
                break;
            case TODO:
                if (userInput.length() <= 5) {
                    throw new EmptyBodyException();
                }
                inputDetail = userInput.substring(5);
                break;
            case EVENT:
                if (userInput.length() <= 6) {
                    throw new EmptyBodyException();
                }
                try {
                    inputDetail = userInput.substring(6, userInput.indexOf("/from"));
                    from = userInput.substring(userInput.indexOf("/from")+6, userInput.indexOf("/to")-1);
                    to = userInput.substring(userInput.indexOf("/to")+4);
                } catch (Exception e) {
                    throw new WrongFormatException("\"deadline content /from time /to time\"");
                }
                break;
            case MARK:
            case UNMARK:
            case DELETE:
                try {
                    this.index = new Integer(userInputSplit[1]) - 1;
                } catch (Exception e) {
                    throw new InvalidKeyException("Please use a valid integer as key");
                }

        }
    }

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
        }
        if (this.currentKey.equals(KeyEnum.INVALID)) {
            // raise InvalidKeyException
            throw new InvalidKeyException();
        }
    }

    public KeyEnum getCurrentKey() {
        return currentKey;
    }

    public void setCurrentKey(KeyEnum currentKey) {
        this.currentKey = currentKey;
    }

    public String getInputDetail() {
        return inputDetail;
    }

    public void setInputDetail(String inputDetail) {
        this.inputDetail = inputDetail;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
