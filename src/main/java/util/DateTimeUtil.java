package util;

import java.time.*;
import java.time.format.*;

public class DateTimeUtil {
    private static final DateTimeFormatter input = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeUtil() {

    }

    public static LocalDateTime format(String s) {
        return LocalDateTime.parse(s, input);
    }

    public static String format(LocalDateTime d) {
        return d.format(input);
    }

    public static LocalDateTime parse(String s) {
        return LocalDateTime.parse(s, input);
    }

    public static boolean isValid(String dateStr) {
        if (dateStr == null || dateStr.isEmpty() || dateStr.equals("null")) {
            return true; // Consider empty or "null" as valid
        }
        try {
            LocalDate.parse(dateStr, input);
            return true;
        } catch (DateTimeParseException ignored) {
            return false;
        }
    }
}

