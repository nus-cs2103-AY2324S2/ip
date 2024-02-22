package duke.item;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.CustomExceptions;
import duke.Parser;

/**
 * Represents a Deadline, which differs from a To-do and an Event
 * in that it contains 1 DateTime member representing when the
 * doneBy time which the task with the deadline must be completed.
 */
public class Deadline implements Item, Serializable {

    private boolean isDone;
    private String name = "";
    private final LocalDateTime doneBy;

    /**
     * Creates a new deadline object. The name, isDone and doneBy
     * field values are obtained from parsing the info argument.
     *
     * @param info a string array obtained from splitting command with
     *             the whitespace regex.
     * @throws CustomExceptions.DeadlineExceptionBy if no /by command was found.
     * @throws CustomExceptions.NamelessTaskException if the name in the command
     *                                                cannot be parsed.
     * @throws CustomExceptions.UnrecognizableDateException if the parser throws
     *                                                      a DateTimeParseException.
     */
    public Deadline(String[] info) throws CustomExceptions {
        int index = 1;
        String doneByString = "";
        this.isDone = false;

        while (!info[index].equals("/by")) {
            if (index >= info.length - 1) {
                throw new CustomExceptions.DeadlineExceptionBy("Please use /by command after deadline name");
            }
            this.name += info[index] + " ";
            index++;
        }
        this.name = this.name.trim();
        if (this.name.equals("")) {
            throw new CustomExceptions.NamelessTaskException("Please re-enter duke.item.Deadline with a valid name");
        }

        for (int i = index + 1; i < info.length; i++) {
            doneByString += info[i] + " ";
        }
        try {
            if (doneByString.trim().equals("")) {
                this.doneBy = LocalDateTime.now();
            } else {
                this.doneBy = Parser.parseDtString(doneByString.trim());
            }
        } catch (DateTimeParseException e) {
            throw new CustomExceptions.UnrecognizableDateException("Date format is unrecognizable, try dd/mm/yy hhmm");
        }
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String doneMessage() {
        return "Nice! I've marked this task as done:\n"
                + this.toString();
    }

    @Override
    public String undoneMessage() {
        return "OK, I've marked this task as not done yet:\n"
                + this.toString();
    }

    @Override
    public String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    @Override
    public String addMessage(int num) {
        return "Got it. I've added this task:\n"
                + this.toString()
                + "\nNow you have " + num + " tasks in the list.";
    }

    @Override
    public String removeMessage(int num) {
        return "Noted. I've removed this task:\n"
                + this.toString()
                + "\nNow you have " + num + " tasks in the list.";
    }

    @Override
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public LocalDateTime getTimeToSortBy() {
        return this.doneBy;
    }

    @Override
    public String toString() {
        return "[D][" + printChecked(this.isDone) + "] " + this.name + " " + "(by: "
                + this.doneBy.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + ")";
    }
}
