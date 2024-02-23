package duke;

import java.time.format.DateTimeFormatter;
/**
 * A class to group all the constants used together.
 * <p>
 * This class should not be instantiated.
 */
public class Constants {
    public static final String CHATBOT_NAME = "Echo";
    public static final String WINDOW_TITLE = "Echo --- Task scheduling program";
    public static final DateTimeFormatter INPUT_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter OUTPUT_FORMATTER =
        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
}
