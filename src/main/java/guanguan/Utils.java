package guanguan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class that deals with time conversions.
 */
public class Utils {
    /**
     * Converts string to LocalDate object.
     *
     * @param datetime string to be converted
     * @return LocalDate object
     * @throws GgException if datetime is in the incorrect format
     */
    public static LocalDate convertStringToDateTime(String datetime) throws GgException {
        int lenDatetime = datetime.length();
        LocalDate localDate;
        // date format: 2019-12-01
        if (lenDatetime == 10) {
            localDate = LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            throw new GgException("Invalid datetime format!");
        }
        return localDate;
    }

    /**
     * Converts LocalDate object to string that is printed on the terminal.
     *
     * @param datetime LocalDate object to be converted
     * @return datetime string that will be printed on the terminal
     */
    public static String printTime(LocalDate datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return datetime.format(formatter);
    }

    /**
     * Converts LocalDate object to string that is stored in the text file.
     *
     * @param datetime LocalDate object to be converted
     * @return datetime string that will be stored in the text file
     */
    public static String convertDateTimeToString(LocalDate datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return datetime.format(formatter);
    }

    /**
     * Reverses a list of strings and converts them to integers.
     *
     * @param list list of strings
     * @return list of integers
     */
    public static List<Integer> reverse(String[] list) {
        List<Integer> indexList = Arrays.stream(list)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Collections.reverse(indexList);
        return indexList;
    }
}
