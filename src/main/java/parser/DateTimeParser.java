package parser;

import jux.Jux;
import jux.JuxException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateTimeParser {
    public static final String[] POSSIBLE_DAY_OF_WEEK_FORMATS = {"E", "EEE", "EEEEE"};
    public static final String[] POSSIBLE_DATETIME_FORMATS = {
            "yyyy-MM-dd HH:mm",
            "dd-MM-yyyy HH:mm",
            "yyyyMMdd HH:mm",
            "yyyy MM dd HH:mm",
            "dd MM yyyy HH:mm",
            "ddMMyyyy HH:mm"
            // Add more formats as needed
    };
    /**
     * Convert the localDateTime variable in the task to
     * a presentable format
     * @param localDateTime date time stored in the task
     * @return formatted string to be printed
     */
    public static String convertDateTimeToStringUI(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if (localDateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        }

        return localDateTime.format(formatter);
    }

    /**
     * Converts the localDateTime date to string to be stored in the datafile
     * @param localDateTime date time
     * @return formatted string to be stored as ISO date time format
     */
    public static String convertDateTimeToStringStorage(LocalDateTime localDateTime) {
        String formattedDateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return formattedDateTime;
    }

    /**
     * Converts the String dateTime in the storage to LocalDateTime
     * @param date date string stored in storage as ISO date time format
     * @return the local date time to be stored in the task
     */
    public static LocalDateTime storageConvertToDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Converts the String input to LocalDateTime
     * @param dateString String date input
     * @return local date time format
     * @throws DateTimeException if invalid format
     */
    public static LocalDateTime inputConvertToDateTime(String dateString) throws JuxException{
        try {
            String dateTimeString = addsTimeIfNonexistent(dateString);
            if (isDayOfWeek(dateTimeString)) {
                return getLocalDateTimeFromDayOfWeek(dateTimeString);
            }
            LocalDateTime dateTime = getLocalDateTimeFromPossibleFormats(dateTimeString);
            return dateTime;
        } catch (JuxException e) {
            throw e;
        }
    }

    private static LocalDateTime getLocalDateTimeFromPossibleFormats(String dateString) throws JuxException {
        for (String format : POSSIBLE_DATETIME_FORMATS) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(format));
                return dateTime;
            } catch (Exception ignored) {
                // If parsing fails, ignore the exception and try the next format
            }
        }
        throw new JuxException("enter date and time using yyyy MM dd HH:mm");
    }

    private static boolean isDayOfWeek(String dateTime) {
        String date = dateTime.split("\\s+")[0];
        for (String format : POSSIBLE_DAY_OF_WEEK_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                DayOfWeek.from(formatter.parse(date));
                return true;
            } catch (Exception ignored) {
                // If parsing fails, ignore the exception and try the next format
            }
        }
        return false;
    }
    private static LocalDateTime getLocalDateTimeFromDayOfWeek(String dateString) throws DateTimeException {
        String[] splitdateString = dateString.split("\\s+");
        LocalTime chosenTime = LocalTime.parse(splitdateString[1], DateTimeFormatter.ofPattern("HH:mm"));
        for (String format : POSSIBLE_DAY_OF_WEEK_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                DayOfWeek dayOfWeek = DayOfWeek.from(formatter.parse(splitdateString[0]));
                LocalDate currentDate = LocalDate.now();
                LocalDate currentDay = currentDate.with(dayOfWeek);
                LocalDate nextOccurrence = findNextDayOfWeek(currentDate, dayOfWeek);
                nextOccurrence = checkIfTimeHasPassedOnSameDayAsCurrent(chosenTime, dayOfWeek,
                        currentDay, nextOccurrence);
                LocalDateTime dateTime = nextOccurrence.atTime(chosenTime);
                return dateTime;
            } catch (Exception ignored) {
                // If parsing fails, ignore the exception and try the next format
            }
        }
        throw new DateTimeException("Invalid format for day of week");
    }

    private static LocalDate checkIfTimeHasPassedOnSameDayAsCurrent(LocalTime time, DayOfWeek dayOfWeek, LocalDate currentDay, LocalDate nextOccurrence) {
        if (nextOccurrence.with(dayOfWeek) == currentDay) {
            LocalTime currentTime = LocalTime.now();
            if (time.isBefore(currentTime)) {
                nextOccurrence = nextOccurrence.plusWeeks(1);
            }
        }
        return nextOccurrence;
    }

    private static LocalDate findNextDayOfWeek(LocalDate currentDate, DayOfWeek targetDayOfWeek) {
        return currentDate.with(TemporalAdjusters.nextOrSame(targetDayOfWeek));
    }
    private static String addsTimeIfNonexistent(String dateString) {
        if (!dateString.contains(":")) { // might consider using localtime to find time
            dateString = dateString + " 00:00";
        }
        return dateString;
    }


}
