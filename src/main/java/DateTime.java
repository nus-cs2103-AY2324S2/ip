import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private static final String pattern = "MMM d yyyy";

    public static String dateToString(LocalDate d) {
        return d.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate stringToDate(String s) throws ConvoBotException {
        LocalDate d;
        try {
            d = LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new ConvoBotException("Invalid input. Wrong date format.");
        }
        return d;
    }
}
