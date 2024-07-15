package knight;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate time;
    Deadline(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
        assert this.time != null : "Time should not be null";
    }

    /**
     * Get the command representation of the task.
     *
     * @return The command representation of the task.
     */
    @Override
    String getCommand() {
        return "deadline " + name + " /by " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(Task.DATE_FORMATTER) + ")";
    }

    @Override
    void update(String updateMessage) {
        String[] params = updateMessage.split(" /by ");
        if (params.length != 2) {
            throw new NonstandardCommandException(
                    "Thou shouldst specify the new name and time of the deadline in the format:\n"
                            + "update [index] [new name] /by [new time]");
        }
        this.name = params[0];
        this.time = LocalDate.parse(params[1]);
    }
}
