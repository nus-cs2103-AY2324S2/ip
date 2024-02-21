package duke;

import java.time.format.DateTimeFormatter;
/**
 * A class to group all the constants used together.
 * <p>
 * This class should not be instantiated.
 */
public class Constants {
    public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter OUTPUT_FORMATTER =
        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
}
