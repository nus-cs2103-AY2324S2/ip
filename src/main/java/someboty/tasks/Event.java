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
    public String taskToCsv() {
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

    /**
     * Creates a new Deadline task with the given details read from the csv file.
     * 
     * @param details Array of string describing the deadline.
     * @return An instance of Deadline with the given details.
     */
    protected static Event csvToTask(String[] details) {
        assert details.length == 5 : "Event:csvToTask An event line in the csv file is broken.";

        boolean isCompleted = details[1].equals("1");
        String description = details[2];
        String from = details[3];
        String to = details[4];

        Event task = new Event(description, from, to);
        task.setStatus(isCompleted);
        
        return task;
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
