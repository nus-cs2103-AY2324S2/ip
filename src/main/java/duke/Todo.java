package duke;

/**
 * Represents a "Todo" task in the Duke application.
 */
public class Todo extends Task {

	/**
	 * Constructs a new Todo task with the specified description and input.
     *
	 * @param description The task's description.
	 * @param input       The original input string that created the task.
	 */
	Todo(String description, String input) {
		super(description, input);
	}

	/**
	 * Returns a string representation of the Todo task.
	 *
	 * @return A string that represents the Todo task.
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	/**
	 * Marks the Todo task as done and prints a confirmation message.
	 */
	@Override
	public String markComplete() {
		super.setComplete();
		return "\tNice! I've marked this task as done:\n\t" + this.toString();
	}

	/**
	 * Marks the Todo task as not done and prints a confirmation message.
	 */
	@Override
	public String unmarkComplete() {
		super.setIncomplete();
		return "\tOK, I've marked this task as not done yet:\n\t" + this.toString();
	}

	/**
	 * Returns a string format of the Todo task for file storage.
	 *
	 * @return A string representation of the Todo task for file storage.
	 */
	@Override
	public String toFileFormat() {
		return String.format("Todo | %s | %s", super.statusNumber, super.input);
	}
}