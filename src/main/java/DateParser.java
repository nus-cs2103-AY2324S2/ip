import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateParser class
 */
public class DateParser {

  public static LocalDate parseDate(String dateString) {
      try {
          return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      } catch (DateTimeParseException e) {
          // Handle parsing exception or return null based on your requirements
          // e.printStackTrace();
          return null;
      }
  }

  public static LocalDateTime parseDateTime(String dateTimeString) {
      try {
          return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
      } catch (DateTimeParseException e) {
          // Handle parsing exception or return null based on your requirements
          // e.printStackTrace();
          return null;
      }
  }

  public static LocalTime parseTime(String timeString) {
      try {
          return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
      } catch (DateTimeParseException e) {
          // Handle parsing exception or return null based on your requirements
          // e.printStackTrace();
          return null;
      }
  }
}