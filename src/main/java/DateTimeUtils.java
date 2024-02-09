import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DateTimeUtils {
   private static final String[] PATTERNS = {
    "d/M/yyyy H:mm", "d/M/yyyy h:mm a", "d/M/yyyy h:mma", "d/M/yyyy h a", "d/M/yyyy ha",
    "d/M H:mm", "d/M h:mm a", "d/M h:mma", "d/M h a", "d/M ha",
    "d MMM yyyy H:mm", "d MMM yyyy h:mm a", "d MMM yyyy h:mma", "d MMM yyyy h a", "d MMM yyyy ha",
    "d MMM H:mm", "d MMM h:mm a", "d MMM h:mma", "d MMM h a", "d MMM ha",
    "MMM d yyyy H:mm", "MMM d yyyy h:mm a", "MMM d yyyy h:mma", "MMM d yyyy h a", "MMM d yyyy ha",
    "MMM d H:mm", "MMM d h:mm a", "MMM d h:mma", "MMM d h a", "MMM d ha",
    "d/M/yyyy", "d/M",
    "d MMM yyyy", "d MMM",
    "MMM d yyyy", "MMM d"
  };

  private static final List<DateTimeFormatter> FORMATTERS = Arrays.stream(PATTERNS)
    .map(pattern -> new DateTimeFormatterBuilder()
                     .appendPattern(pattern)
                     .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
                     .parseDefaulting(ChronoField.HOUR_OF_DAY, 23) // Default to end of day (23:59)
                     .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                     .parseCaseInsensitive()
                     .toFormatter(Locale.ENGLISH))
    .collect(Collectors.toList());
  
  public static LocalDateTime parseDateTime(String dateTimeStr, LocalDate defaultDate) throws DateTimeParseException {
    for (DateTimeFormatter formatter : FORMATTERS) {
      try {
        return LocalDateTime.parse(dateTimeStr, formatter);
      } catch (DateTimeParseException ignored) {
        // Ignore and try next formatter
      }
    }
    throw new DateTimeParseException("Invalid date or time format", dateTimeStr, 0);
  }

  public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
    return parseDateTime(dateTimeStr, LocalDate.now());
  }
}

