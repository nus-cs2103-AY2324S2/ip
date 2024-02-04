package Riri;
public class Deadline extends Task {
    protected String by;
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    /** Static method to create a Deadline object from a formatted string
     * @param inputString string to parse to Deadline object
     * @return Deadline object
     */
    public static Deadline parseDeadlineFromString(String inputString) {
        // Assuming the inputString is formatted as "[D][ ] task description (by: deadline)"
        int indexOfOpeningBracket = inputString.indexOf('[');
        int indexOfClosingBracket = inputString.indexOf(']');
        int indexOfBy = inputString.indexOf("(by:");

        char status = inputString.charAt(4);
        String taskDescription = inputString.substring(indexOfClosingBracket + 4, indexOfBy).trim();
        String by = inputString.substring(indexOfBy + 4, inputString.length() - 1).trim();

        Deadline deadline = new Deadline(taskDescription, by);

        // Check the status and mark the Deadline as done if needed
        if (status == 'X') {
            deadline.markDone();
        }

        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}