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

    public static ArrayList<String> parseTodoInput(String[] splitMessages) throws ByteTalkerException.TodoUnsupportedFormatException, ByteTalkerException.TodoNoTaskException {
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            determineSupportedTodoTask(splitMessages[i]);
            tempMessage += splitMessages[i] + " ";
        }
        ArrayList<String> messageContainer = new ArrayList<>();
        messageContainer.add(tempMessage.strip());
        determineTodoTaskContent(messageContainer);
        return messageContainer;
    }

    private static void determineSupportedTodoTask(String message) throws ByteTalkerException.TodoUnsupportedFormatException {
        if (message.equals("/by") || message.equals("/from") || message.equals("to")) {
            throw new ByteTalkerException.TodoUnsupportedFormatException("This format is not supported for " +
                    "Todo");
        }
    }

    private static void determineTodoTaskContent(ArrayList<String> parsedTodoInputs) throws ByteTalkerException.TodoNoTaskException {
        if (parsedTodoInputs.get(0).isEmpty()) {
            throw new ByteTalkerException.TodoNoTaskException("No Task");
        }
    }

    public static ArrayList<String> parseDeadlineInput(String[] splitMessages) throws ByteTalkerException.DeadlineUnsupportedFormatException, ByteTalkerException.DeadlineWrongFormatException {
        ArrayList<String> messageContainer = new ArrayList<>();
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            determineSupportedDeadlineTask(splitMessages[i]);
            boolean isContentFilled = splitMessages[i].equals("/by");
            if (isContentFilled) {
                messageContainer.add(tempMessage.strip());
                tempMessage = "";
            } else {
                tempMessage += splitMessages[i] + " ";
            }
        }
        messageContainer.add(tempMessage);
        determineDeadlineTaskContentAndTime(messageContainer);
        return messageContainer;
    }

    private static void determineSupportedDeadlineTask(String message) throws ByteTalkerException.DeadlineUnsupportedFormatException {
        if (message.equals("/from") || message.equals("/to")) {
            throw new ByteTalkerException.DeadlineUnsupportedFormatException("This format is not supported for " +
                    "Deadline");
        }
    }

    private static void determineDeadlineTaskContentAndTime(ArrayList<String> parsedDeadlineInputs) throws ByteTalkerException.DeadlineWrongFormatException {
        if (parsedDeadlineInputs.get(0).isEmpty() || parsedDeadlineInputs.size() != 2) {
            throw new ByteTalkerException.DeadlineWrongFormatException("This is wrong format for deadline");
        }
    }

    public static ArrayList<String> parseEventInput(String[] splitMessages) throws ByteTalkerException.EventWrongFormatException {
        ArrayList<String> messageContainer = new ArrayList<>();
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            determineSupportedEventTask(splitMessages[i]);
            boolean isContentFilled = splitMessages[i].equals("/from");
            boolean isFromFilled = splitMessages[i].equals("/to");
            if (isContentFilled) {
                messageContainer.add(tempMessage.strip());
                tempMessage = "";
            } else if (isFromFilled) {
                messageContainer.add(tempMessage.strip());
                tempMessage = "";
            } else {
                tempMessage += splitMessages[i] + " ";
            }
        }
        messageContainer.add(tempMessage.strip());
        determineEventTaskContentAndTime(messageContainer);
        return messageContainer;
    }

    private static void determineSupportedEventTask(String message) throws ByteTalkerException.EventWrongFormatException {
        if (message.equals("/by")) {
            throw new ByteTalkerException.EventWrongFormatException("This is the wrong for event");
        }
    }

    private static void determineEventTaskContentAndTime(ArrayList<String> parsedEventInputs) throws ByteTalkerException.EventWrongFormatException {
        if (parsedEventInputs.size() != 3 || parsedEventInputs.get(0).isEmpty()
                || parsedEventInputs.get(1).isEmpty()
                || parsedEventInputs.get(2).isEmpty()) {
            throw new ByteTalkerException.EventWrongFormatException("This is wrong format for event");
        }
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        String cleanDateTimeString = dateTimeString.strip();
        boolean isTimeExist = cleanDateTimeString.split(" ").length > 1;
        boolean isInputFirstFormat = dateTimeString.split("-").length > 1;

        if (!isTimeExist) {
            cleanDateTimeString += " 2359";
        }

        DateTimeFormatter inputFormatter = determineDateInputFormat(isInputFirstFormat);

        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(cleanDateTimeString, inputFormatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            Ui.showDateTimeParseErrorMsg(e);
            return dateTime;
        }
    }

    private static DateTimeFormatter determineDateInputFormat(boolean isInputFirstFormat) {
        if (isInputFirstFormat) {
            return DateTimeFormatter.ofPattern("yyyy-M-d Hmm");
        } else {
            return DateTimeFormatter.ofPattern("d/M/yyyy Hmm");
        }
    }
}
