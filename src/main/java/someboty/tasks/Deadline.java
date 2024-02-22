package someboty.tasks;

import java.time.LocalDateTime;

import someboty.exceptions.InputException;
import someboty.managers.DateManager;

/**
 * Handles a task of type "Deadline".
 * Deadline tasks contains both the description and deadline of the task.
*/
public class Deadline extends Task {

    /* --- VARIABLES --- */
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     * @param description Raw description of the deadline task.
     */
    public Deadline(String description) {
        super("");
        this.formatInput(description);
    }

    /**
     * Constructor for Deadline.
     * @param name Name of the deadline task.
     * @param stringDeadline String representation of the task's deadline.
     */
    public Deadline(String name, String stringDeadline) {
        super(name);
        this.deadline = DateManager.parseDate(stringDeadline);
    }

    @Override
    protected void formatInput(String description) {
        try {
            String stringDeadline = description.split("/by ")[1].trim();
            this.deadline = DateManager.parseDate(stringDeadline);

        } catch (ArrayIndexOutOfBoundsException e){
            // occurs when missing "/by " in command
            throw new InputException(
                "Unable to identify the deadline date. Make sure to follow the format:\n"
                + "'deadline DESCRIPTION /by VALID_DATE_FORMAT'"
                );
        }

        this.name = description.split("/by")[0].trim();
    }

    @Override
    public String toCSV() {
        char status = this.isCompleted()
                            ? '1'
                            : '0';

        return String.format("D,%c,%s,%s\n",
                    status,
                    this.name,
                    DateManager.printDate(this.deadline)
                    );
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: [%s])", 
                    super.toString(),
                    DateManager.printDate(this.deadline)
                    );
    }
}
