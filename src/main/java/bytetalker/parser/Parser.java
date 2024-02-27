package bytetalker.parser;

import bytetalker.exception.CommandWrongFormatExcpetion;
import bytetalker.exception.TodoUnsupportedFormatException;
import bytetalker.exception.DeadlineUnsupportedFormatException;
import bytetalker.exception.EventUnsupportedFormatException;

import bytetalker.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents utility class for parsing user input into arrays.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-01-28
 */
public class Parser {
    /**
     * Separates user input by single space and store them in String array.
     *
     * @param input User input.
     * @return Parsed user input.
     */
    public static String[] parse(String input) {
        input = input.strip();
        String[] tempMessages = input.split(" ");
        return tempMessages;
    }

    /**
     * Creates an ArrayList<String> of length 1.
     * First element is content.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return ArrayList containing content of the task.
     * @throws TodoUnsupportedFormatException
     */
    public static String[] parseTodoAddInput(String[] splitMessages)
            throws TodoUnsupportedFormatException {
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            determineSupportedTodoTask(splitMessages[i]);
            tempMessage += splitMessages[i] + " ";
        }
        String[] messageContainer = new String[1];
        messageContainer[0] = tempMessage.strip();
        determineTodoTaskContent(messageContainer);
        return messageContainer;
    }

    private static void determineSupportedTodoTask(String message)
            throws TodoUnsupportedFormatException {
        if (message.equals("/by") || message.equals("/from") || message.equals("to")) {
            throw new TodoUnsupportedFormatException();
        }
    }

    private static void determineTodoTaskContent(String[] parsedTodoInputs)
            throws TodoUnsupportedFormatException {
        if (parsedTodoInputs[0] == null || parsedTodoInputs[0].isEmpty()) {
            throw new TodoUnsupportedFormatException();
        }
    }

    /**
     * Creates an ArrayList<String> of length 2.
     * First element is content of the task.
     * Second element is the deadline of the task.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return ArrayList containing content and deadline of the task.
     * @throws DeadlineUnsupportedFormatException
     */
    public static String[] parseDeadlineAddInput(String[] splitMessages)
            throws DeadlineUnsupportedFormatException {
        String[] messageContainer = new String[2];
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            determineSupportedDeadlineTask(splitMessages[i]);
            boolean isContentFilled = splitMessages[i].equals("/by");
            if (isContentFilled) {
                messageContainer[0] = tempMessage.strip();
                tempMessage = "";
            } else {
                tempMessage += splitMessages[i] + " ";
            }
        }
        messageContainer[1] = tempMessage;
        determineDeadlineTaskContent(messageContainer);
        return messageContainer;
    }

    private static void determineSupportedDeadlineTask(String message)
            throws DeadlineUnsupportedFormatException {
        if (message.equals("/from") || message.equals("/to")) {
            throw new DeadlineUnsupportedFormatException();
        }
    }

    private static void determineDeadlineTaskContent(String[] parsedDeadlineInputs)
            throws DeadlineUnsupportedFormatException {
        if (parsedDeadlineInputs[0] == null || parsedDeadlineInputs[0].isEmpty()
                || parsedDeadlineInputs[1] == null || parsedDeadlineInputs[1].isEmpty()) {
            throw new DeadlineUnsupportedFormatException();
        }
    }

    /**
     * Creates an ArrayList<String> of length 3.
     * First element is content of the task.
     * Second element is the from of the task.
     * Third element is the to of the task.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return ArrayList containing content, from and to of the task.
     * @throws EventUnsupportedFormatException
     */
    public static String[] parseEventAddInput(String[] splitMessages)
            throws EventUnsupportedFormatException {
        String[] messageContainer = new String[3];
        String tempMessage = "";
        for (int i = 1; i < splitMessages.length; i++) {
            determineSupportedEventTask(splitMessages[i]);
            boolean isContentFilled = splitMessages[i].equals("/from");
            boolean isFromFilled = splitMessages[i].equals("/to");
            if (isContentFilled) {
                messageContainer[0] = tempMessage.strip();
                tempMessage = "";
            } else if (isFromFilled) {
                messageContainer[1] = tempMessage.strip();
                tempMessage = "";
            } else {
                tempMessage += splitMessages[i] + " ";
            }
        }
        messageContainer[2] = tempMessage.strip();
        System.out.println(messageContainer);
        determineEventTaskContentAndTime(messageContainer);
        return messageContainer;
    }

    private static void determineSupportedEventTask(String message)
            throws EventUnsupportedFormatException {
        if (message.equals("/by")) {
            throw new EventUnsupportedFormatException();
        }
    }

    private static void determineEventTaskContentAndTime(String[] parsedEventInputs)
            throws EventUnsupportedFormatException {
        if (parsedEventInputs[0] == null || parsedEventInputs[0].isEmpty()
                || parsedEventInputs[1] == null || parsedEventInputs[1].isEmpty()
                || parsedEventInputs[2] == null || parsedEventInputs[2].isEmpty()) {
            throw new EventUnsupportedFormatException();
        }
    }

    /**
     * Converts user's date time string input into LocalDateTime object.
     * Allows two different forms of input: yyyy-M-d Hmm and d/M/yyyy Hmm.
     *
     * @param dateTimeString User date time string input.
     * @return LocalDateTime object based on input by user.
     */
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

    /**
     * Extracts new value to replace from user input.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return New value to replace.
     */
    public static String parseContentUpdateInput(String[] splitMessages) {
        String content = "";
        for (int i = 3; i < splitMessages.length; i++) {
            content += splitMessages[i] + " ";
        }
        return content.strip();
    }

    /**
     * Extracts new date time value to replace from user input.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return New LocalDateTime object based on user input.
     */
    public static LocalDateTime parseDateTimeUpdateInput(String[] splitMessages) {
        String time = "";
        for (int i = 3; i < splitMessages.length; i++) {
            time += splitMessages[i] + " ";
        }
        return parseDateTime(time.strip());
    }

    /**
     * Extracts words of contents that user wants to find.
     *
     * @param splitMessages Parsed messages of user input and processed by Parser.
     * @return Words of contents that user wants to find.
     */
    public static String parseFindInput(String[] splitMessages) {
        String temp = "";
        for (int i = 1; i < splitMessages.length; i++) {
            temp += splitMessages[i] + " ";
        }
        return temp.strip();
    }

    public static void checkCommand(String[] splitMessages) throws CommandWrongFormatExcpetion {
        if (splitMessages.length <= 1) {
            throw new CommandWrongFormatExcpetion("Wrong command format. Please follow the correct format.");
        }
    }
}
