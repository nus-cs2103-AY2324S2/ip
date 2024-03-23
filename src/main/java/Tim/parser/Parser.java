package Tim.parser;

import Tim.commands.Command;
import Tim.exception.TimException;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static Tim.exception.ErrorMessages.MESSAGE_INVALID_DATE_FORMAT;

public interface Parser<T extends Command> {

    static final Pattern DATE_FORMAT = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");

    T parse(String args, Path filePath) throws TimException;

    /**
     * Translate the date of String type into a LocalDate type.
     * @param msg date in String form
     * @return LocalDate
     * @throws TimException
     */
    public static LocalDate getDate(String msg) throws TimException {
        try {
            Matcher m = DATE_FORMAT.matcher(msg);
            if (m.matches()) {
                String month = String.format("%02d", Integer.parseInt(m.group(2)));
                String day = String.format("%02d", Integer.parseInt(m.group(1)));
                String dateString = m.group(3) + "-" + month + "-" + day;
                return LocalDate.parse(dateString);
            } else {
                throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
        } catch (DateTimeParseException e) {
            throw new TimException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }
}
