package duke;
public class Todo extends Task {
	Todo(String description, String input) {
		super(description, input);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	@Override
	public void mark() {
		super.setComplete();
		System.out.println("\tNice! I've marked this task as done:\n\t" + this.toString());
	}

	@Override
	public void unmark() {
		this.setIncomplete();
		System.out.println("\tOK, I've marked this task as not done yet:\n\t" + this.toString());
	}

	public String toFileFormat() {
		return String.format("Todo | %s | %s", super.statusNumber, super.input);
	}
}
