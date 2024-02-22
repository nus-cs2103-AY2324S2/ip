package duke.constants;

import java.time.format.DateTimeFormatter;

/**
 * Utility class that defines constants used in the application.
 */
public class Constant {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FORMATTER_FOR_PRINT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_PRINT = DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm");
    public static final String RELATIVE_PATH = "data/duke.txt";
}
