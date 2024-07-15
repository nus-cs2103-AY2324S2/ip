package knight;

import java.time.LocalDate;

/**
 * Represents a task that starts and ends at a specific time.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;
    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
        assert this.startTime != null : "Time should not be null";
        assert this.endTime != null : "Time should not be null";
    }

    /**
     * Get the command representation of the task.
     *
     * @return The command representation of the task.
     */
    @Override
    String getCommand() {
        return "event " + name + " /from " + startTime + " /to " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(Task.DATE_FORMATTER)
                + " to: " + endTime.format(Task.DATE_FORMATTER) + ")";
    }

    @Override
    void update(String updateMessage) {
        String[] params = updateMessage.split(" /from | /to ");
        if (params.length != 3) {
            throw new NonstandardCommandException(
                    "Thou shouldst specify the new name, start time and end time of the event in the format:\n"
                            + "update [index] [new name] /from [new start time] /to [new end time]");
        }
        this.name = params[0];
        this.startTime = LocalDate.parse(params[1]);
        this.endTime = LocalDate.parse(params[2]);
    }
}
