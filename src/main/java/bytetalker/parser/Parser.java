package bytetalker.parser;

import bytetalker.exception.ByteTalkerException;
import bytetalker.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public static String[] parse(String input) {
        input = input.strip();
        String[] tempMessages = input.split(" ");
        return tempMessages;
    }

    public static ArrayList<String> parseTodoInput(String[] splitMessages) throws ByteTalkerException.TodoUnsupportedFormatException {
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            if (splitMessages[i].equals("/by") || splitMessages[i].equals("/from") || splitMessages[i].equals("to")) {
                throw new ByteTalkerException.TodoUnsupportedFormatException("This format is not supported for " +
                        "Todo");
            }
            tempMessage += splitMessages[i] + " ";
        }
        ArrayList<String> resultMessages = new ArrayList<>();
        tempMessage = tempMessage.strip();
        resultMessages.add(tempMessage);
        return resultMessages;
    }

    public static ArrayList<String> parseDeadlineInput(String[] splitMessages) throws ByteTalkerException.DeadlineUnsupportedFormatException {
        ArrayList<String> messageContainer = new ArrayList<>();
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            if (splitMessages[i].equals("/from") || splitMessages[i].equals("/to")) {
                throw new ByteTalkerException.DeadlineUnsupportedFormatException("This format is not supported for " +
                        "Deadline");
            }
            boolean isContentFilled = splitMessages[i].equals("/by");
            if (isContentFilled) {
                tempMessage = tempMessage.strip();
                messageContainer.add(tempMessage);
                tempMessage = "";
            } else {
                tempMessage += splitMessages[i] + " ";
            }
        }
        messageContainer.add(tempMessage);
        return messageContainer;
    }

    public static ArrayList<String> parseEventInput(String[] splitMessages) throws ByteTalkerException.EventWrongFormatException {
        ArrayList<String> messageContainer = new ArrayList<>();
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            if (splitMessages[i].equals("/by")) {
                throw new ByteTalkerException.EventWrongFormatException("This is the wrong for event");
            }
            boolean isContentFilled = splitMessages[i].equals("/from");
            boolean isFromFilled = splitMessages[i].equals("/to");
            if (isContentFilled) {
                tempMessage = tempMessage.strip();
                messageContainer.add(tempMessage);
                tempMessage = "";
            } else if (isFromFilled) {
                tempMessage = tempMessage.strip();
                messageContainer.add(tempMessage);
                tempMessage = "";
            } else {
                tempMessage += splitMessages[i] + " ";
            }
        }
        tempMessage = tempMessage.strip();
        messageContainer.add(tempMessage);
        return messageContainer;
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        String cleanDateTimeString = dateTimeString.strip();
        boolean isTimeExist = cleanDateTimeString.split(" ").length > 1;
        boolean isInputFirstFormat = dateTimeString.split("-").length > 1;
        DateTimeFormatter inputFormatter;
        if (!isTimeExist) {
            cleanDateTimeString += " 2359";
        }

        if (isInputFirstFormat) {
            inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d Hmm");
        } else {
            inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy Hmm");
        }

        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(cleanDateTimeString, inputFormatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            Ui.showDateTimeParseErrorMsg(e);
            return dateTime;
        }
    }
}
