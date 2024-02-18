package someboty.tasks;

import java.time.LocalDateTime;

import someboty.exceptions.InputException;
import someboty.managers.DateManager;

/**
* Handles a task of type "Event".
* Event tasks contains the following details:
* 1) Description of the task.
* 2) Event start date/time.
* 3) Event end date/time.
*/
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description) {
        super("");
        this.formatInput(description);
    }

    public Event(String name, String stringFrom, String stringTo) {
        super(name);
        this.from = DateManager.parseDate(stringFrom);
        this.to = DateManager.parseDate(stringTo);
    }

    @Override
    protected void formatInput(String description) {
        String stringFrom, stringTo;

        try {
            this.name = description.split("/")[0].trim();
            stringFrom = description.split("/from ")[1].split("/to ")[0].trim();
            stringTo = description.split("/to ")[1].split("/from ")[0].trim();

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputException(
                "Unable to identify the start and/or end date. Make sure to follow the format:\n"
                + "event DESCRIPTION /from START /to END"
                );
        }

        this.from = DateManager.parseDate(stringFrom);
        this.to = DateManager.parseDate(stringTo);
    }

    @Override
    public String toCSV() {
        char status = this.isCompleted()
                            ? '1'
                            : '0';

        return String.format("E,%c,%s,%s,%s\n",
                    status,
                    this.name,
                    DateManager.printDate(this.from),
                    DateManager.printDate(this.to)
                    );
    }
    
    @Override
    public String toString() {
        return String.format("[E]%s (from: [%s], to: [%s])",
                    super.toString(),
                    DateManager.printDate(this.from),
                    DateManager.printDate(this.to)
                    );
    }
}
