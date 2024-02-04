package Duke.activityAndUtility;

/**
 * The {@code Activity} interface defines the structure for activities that can be managed within the Duke application.
 * It provides methods for printing details about an activity, retrieving the name of the activity, and marking an activity
 * with a specific input, potentially to track completion or progress.
 */
public interface Activity {
    /**
     * Prints the details about the activity. This could include information such as the activity's name,
     * status (completed or not), and any other relevant details specific to the implementation.
     */
    public void printActivity();

    /**
     * Retrieves the name of the activity.
     *
     * @return A {@code String} representing the name of the activity.
     */
    public String getName();

    /**
     * Marks the activity with a given input. The interpretation of the input is implementation-specific
     * and could be used to mark the activity as completed, set its priority, or any other relevant action.
     *
     * @param input A {@code String} input that affects the activity's state or properties.
     */
    public void mark(String input);
}
