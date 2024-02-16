package bytebuddy.constants;

/**
 * The {@code Formats} class contains constants representing task input formats used in the application.
 */
public class Formats {
    public static final String EVENT_FORMAT = "event [task] /from [date] /to [date]";
    public static final String DEADLINE_FORMAT = "deadline [task] /by [date]";
    public static final String FIND_FORMAT = "find [keyword1] OR find [keyword1], [keyword2] ... for multiple values";
}
