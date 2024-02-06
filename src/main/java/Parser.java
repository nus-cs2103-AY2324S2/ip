import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static String[] parse(String input) {
        input = input.strip();
        String[] tempMessages = input.split(" ");
        return tempMessages;
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
            inputFormatter = DateTimeFormatter.ofPattern("yy-M-d Hmm");
        } else {
            inputFormatter = DateTimeFormatter.ofPattern("d/M/yy Hmm");
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
