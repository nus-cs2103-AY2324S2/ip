package chatbot.value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * This represents minimally a {@link String},
 * but can also represent a {@link LocalDate} <b>if possible</b>.
 * <p>
 * If the string value is in a suitable date format,
 * it is stored as a {@link LocalDate},
 * and printed as a date in the {@value DISPLAY_PATTERN} format.
 *
 * @author Titus Chew
 */
public final class DateStringValue extends StringValue {
    /** The {@link String} pattern for displaying dates. */
    private static final String DISPLAY_PATTERN = "MMM d yyyy";

    /** The {@link DateTimeFormatter} for displaying dates. */
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DISPLAY_PATTERN, Locale.ENGLISH);

    /** The {@link DateTimeFormatter} for a string value. */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DISPLAY_DATE_TIME_FORMATTER)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH))
            .toFormatter();

    /** The {@link LocalDate} value stored. */
    private final LocalDate dateValue;

    /**
     * Takes in a string, but tries to convert it to a {@link LocalDate}.
     *
     * @param value The value as a {@link String}.
     */
    public DateStringValue(String value) {
        super(value);

        LocalDate date = null;
        try {
            date = LocalDate.parse(super.toString(), DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            // invalid date, but this is expected if the string is not a date, so we do nothing
        } finally {
            this.dateValue = date;
        }
    }

    @Override
    public String toString() {
        if (dateValue != null) {
            return dateValue.format(DISPLAY_DATE_TIME_FORMATTER);
        } else {
            return super.toString();
        }
    }
}
