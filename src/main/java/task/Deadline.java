package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructor for a Deadline.
     * @param description Description of the Event.
     * @param deadline DateTime object to represent deadline DateTime.
     * */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for a Deadline.
     * @param description Description of the Event.
     * @param isDone Boolean value to state if the task is done or not.
     * @param deadline DateTime object to represent deadline DateTime.
     * */
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Prints the task in the console.
     * */
    @Override
    public String taskPrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");
        String result = "    " + ".[D]" + getStatusIcon() + " " + description
                + System.lineSeparator()
                + "        " + "BY: " + deadline.format(output)
                + System.lineSeparator()
                + "        " + this.printTags();

        if (this.tags.isEmpty()) {
            result = "    " + ".[D]" + getStatusIcon() + " " + description
                    + System.lineSeparator()
                    + "        " + "BY: " + deadline.format(output);
        }

        return result;
    }

    /**
     * Prints the task and its corresponding index in the list of tasks in the console.
     * */
    @Override
    public String taskPrinter(int index) {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");

        String result = "    " + (index+1) + ".[D]" + getStatusIcon() + " " + description
                + System.lineSeparator()
                + "        " + "BY: " + deadline.format(output)
                + System.lineSeparator()
                + "        " + this.printTags();

        if (this.tags.isEmpty()) {
            result = "    " + (index+1) + ".[D]" + getStatusIcon() + " " + description
                    + System.lineSeparator()
                    + "        " + "BY: " + deadline.format(output);
        }

        return result;
    }

    /**
     * Prints the task in the format of the saved txt file.
     * */
    @Override
    public String storagePrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String tagString = "|tag";

        if (this.tags.isEmpty()) {
            return "D" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description + "|by" + deadline.format(output);
        }

        for (String t : this.tags) {
            tagString += "_" + t;
        }
        return "D" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description + "|by" + deadline.format(output) + tagString;
    }

    /**
     * Checks if a Deadline is equal to the current instance of Deadline.
     *
     * @param obj The object that is to be checked for equality with current Deadline object.
     * @return True if the obj is equal else return False.
     * */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        if (obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            return deadline.equals(d.deadline);
        }

        return false;
    }
}
