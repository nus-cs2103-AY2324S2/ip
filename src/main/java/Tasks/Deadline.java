package Tasks;

import java.time.LocalDateTime;
import Exceptions.InputException;
import Managers.dateManager;

/**
 * Handles a task of type "Deadline".
 * Deadline tasks contains both the description and deadline of the task.
*/
public class Deadline extends Task {

    /* --- VARIABLES --- */
    private LocalDateTime deadline;

    /* --- CONSTRUCTORS --- */
    public Deadline(String description) {
        super("");
        this.formatInput(description);
    }

    public Deadline(String name, String stringDeadline) {
        super(name);
        this.deadline = dateManager.parseDate(stringDeadline);
    }

    /* --- METHODS --- */
    @Override
    protected void formatInput(String description) {
        try {
            String stringDeadline = description.split("/by ")[1].trim();
            this.deadline = dateManager.parseDate(stringDeadline);

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
        char status = this.isDone
                            ? '1'
                            : '0';

        return String.format("D,%c,%s,%s\n",
                    status,
                    this.name,
                    dateManager.printDate(this.deadline)
                    );
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: [%s])", 
                    super.toString(),
                    dateManager.printDate(this.deadline)
                    );
    }
}
