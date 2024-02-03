import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
   Singleton class to handle the format of date and time
 */
public class DateHandler {

    //Inspired from: https://www.baeldung.com/java-date-regular-expressions
    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{1,2}[-/]\\d{1,2}[-/]\\d{2,4})");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{2}[ :]?\\d{2}?)((?i)\\s?[ap]m)?");

    public static Optional<LocalDate> checkDate(String testDate, int count) throws DukeException {

        Matcher match = DATE_PATTERN.matcher(testDate);
        if (match.groupCount() > count) {
            throw new DukeException("TooManyDates");
        }
        if (match.find()) {
            String[] data = match.group().split("[-/]");
            LocalDate convert = LocalDate.parse(data[2] + "-" + data[1] + "-" + data[0]);
            return Optional.of(convert);

        } else {
            return Optional.empty();
        }
    }

    public static Optional<LocalTime> checkTime(String testTime) {

        Matcher match = TIME_PATTERN.matcher(testTime);
        if (match.find()) {
            String capture = match.group();
            Matcher meridiemMatch = Pattern.compile("[ap]m").matcher(capture);
            if (meridiemMatch.find()) {
                //12 hour time format
                String meridiem = meridiemMatch.group().toLowerCase();
                System.out.println("Using 12 hour time format ");
                capture = capture.replaceAll("[ap]m", "");
                capture = capture.replaceAll("[ :]", "");//remove the blank spaces
                //Split it into groups of two digits
                String[] data = capture.split("(?<=\\G\\d{2})");
                int hour = Integer.parseInt(data[0]);
                int minutes = Integer.parseInt(data[1]);
                if (meridiem.equals("pm")) {

                    hour += 12;
                }
                return Optional.of(LocalTime.of(hour, minutes));

            } else {
                //treat it as a 24 hour time format
                capture = capture.replaceAll("[ :]", "");//remove the blank spaces
                System.out.println("Printing array");
                String[] data = capture.split("(?<=\\G\\d{2})");
                int hour = Integer.parseInt(data[0]);
                int minutes = Integer.parseInt(data[1]);
                return Optional.of(LocalTime.of(hour, minutes));
            }

        }
        return Optional.empty();
    }


}
