package duke;

public class InvalidTask extends Task {
    /**
	 * Constructs a new Todo task with the specified description and input.
	 *
	 * @param description The task's description.
	 * @param input       The original input string that created the task.
	 */
	InvalidTask(String errorMessage) {
		super(errorMessage, "");
	}

	/**
	 * Returns an empty string because it is not needed for file storage.
	 *
	 * @return A string representation of the Todo task for file storage.
	 */
	@Override
	public String toFileFormat() {
		return "";
	}

	/**
	 * Returns the error description string.
	 *
	 * @return A string describing the error during Task creation.
	 */
	@Override
    public String getDetails() {
        return "\t" + this.description;
    }

}
