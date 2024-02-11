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
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DateTimeUtils {

  private static final String[] DATE_PATTERNS = {
    "d/M/yyyy", 
    "d-M-yyyy",
    "d/M/yy", 
    "d-M-yy",
    "dMMyyyy", 
    "dMMyy", 
    "dd/MM/yyyy",
    "dd/MM/yy",
    "dd-MM-yyyy",
    "dd-MM-yy",
    "ddMMyyyy",
    "ddMMyy",
    "d/M",
    "d/MMM/yyyy",
    "d/MMM/yy",
    "d/MMM",
    "MMM/d",
    "d MMM yyyy", 
    "MMM d yyyy", 
    "d MMM", 
    "MMM d",
  };

  private static final String[] DATE_WITHOUT_YEAR_PATTERNS = {
    "d/M",
    "d/MMM",
    "MMM/d",
    "d MMM",
    "MMM d",
  };

  private static final String[] TIME_PATTERNS = {
    "HHmm", 
    "HH:mm", 
    "h:mm a", 
    "h:mma", 
    "h a", 
    "ha",
  };

  private static final List<DateTimeFormatter> FORMATTERS = new ArrayList<>();

  static {
    for (String datePattern : DATE_PATTERNS) {
      for (String timePattern : TIME_PATTERNS) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                    .appendPattern(datePattern + " " + timePattern)
                    .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
                    .parseCaseInsensitive();

        // Check if the time pattern involves AM/PM notation
        if (timePattern.contains("a")) {
          // For AM/PM time patterns, default CLOCK_HOUR_OF_AMPM and AMPM_OF_DAY if necessary
          builder.parseDefaulting(ChronoField.CLOCK_HOUR_OF_AMPM, 11)
                 .parseDefaulting(ChronoField.AMPM_OF_DAY, 1); // Default to PM if not specified
        } else {
          // For 24-hour time patterns or date-only, default to the end of the day
          builder.parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                 .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59);
        }
        DateTimeFormatter formatter = builder.toFormatter(Locale.ENGLISH);
        FORMATTERS.add(formatter);
      }

      // Add a formatter for date only, defaulting to end of the day
      DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .appendPattern(datePattern)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                .parseCaseInsensitive()
                .toFormatter(Locale.ENGLISH);
      FORMATTERS.add(dateFormatter);
    }
  }

  public static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
    // Preprocess the input string to normalize formatting 
    String normalizedDateTimeStr = normalizeDateTimeString(dateTimeStr);

    for (DateTimeFormatter formatter : FORMATTERS) {
      try {
        return LocalDateTime.parse(normalizedDateTimeStr, formatter);
      } catch (DateTimeParseException ignored) {
         // Continue trying with the next formatter
      }
    }
    throw new DateTimeParseException("Invalid date or time format", dateTimeStr, 0);
  }
  private static String normalizeDateTimeString(String dateTimeStr) {
    // Trim leading and trailing whitespace
    String normalized = dateTimeStr.trim();

    // Normalize space around slashes (/) and dashes (-) for dates
    normalized = normalized.replaceAll("\\s*/\\s*", "/");
    normalized = normalized.replaceAll("\\s*-\\s*", "-");

    // Normalize space before AM/PM markers to ensure consistent formatting
    normalized = normalized.replaceAll("\\s+(AM|am|PM|pm)", " $1");

    // Ensure AM/PM markers are uppercase for consistency with patterns (optional)
    normalized = normalized.replaceAll("am", "AM").replaceAll("pm", "PM");

    return normalized;
  }
    
  public static LocalDateTime parseDeadlineDateTime(String deadlineStr) throws DateTimeParseException {
    // Attempt to parse the string directly with existing formatters
    try {
      return parseDateTime(deadlineStr);
    } catch (DateTimeParseException e) {
      // Check if the input matches any of the date without year patterns
      for (String pattern : DATE_WITHOUT_YEAR_PATTERNS) {
        try {
          DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                  .appendPattern(pattern)
                  .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                  .parseDefaulting(ChronoField.HOUR_OF_DAY, 23) // Default to end of the day
                  .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                  .toFormatter(Locale.ENGLISH);
          LocalDate date = LocalDate.parse(deadlineStr, formatter);
          return LocalDateTime.of(date, LocalTime.of(23, 59)); // Use end of the day
        } catch (DateTimeParseException ignored) {
          // Ignore and try the next pattern
        }
      }
      // If none of the patterns match, re-throw the original exception
      throw new DateTimeParseException("Invalid date or time format", deadlineStr, 0);
    }
  }  

  public static LocalDateTime tryParseEndDateTime(String endStr, LocalDate startDate) {
    for (String timePattern : TIME_PATTERNS) {
      try {
        DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
                .appendPattern(timePattern)
                .toFormatter(Locale.ENGLISH);
        LocalTime endTime = LocalTime.parse(endStr, timeFormatter);
          return LocalDateTime.of(startDate, endTime);
      } catch (DateTimeParseException ignored) {
        // Ignore and try the next time pattern
      }
    }
    // If none of the time patterns match, attempt parsing as full LocalDateTime
    return parseDateTime(endStr); // Fallback to using the existing parseDateTime method
  }

}
