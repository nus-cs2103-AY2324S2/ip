package duke.item;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.CustomExceptions;
import duke.Parser;

/**
 * Represents an Event, which differs from a To-do and a Deadline
 * in that it contains 2 DateTime members representing when the
 * event starts and ends respectively.
 */
public class Event implements Item, Serializable {
    private boolean isDone = false;
    private String name = "";
    private LocalDateTime start = LocalDateTime.now();
    private LocalDateTime end = LocalDateTime.now();

    /**
     * Creates a new deadline object. The name, isDone, start and end
     * field values are obtained from parsing the info argument.
     *
     * @param info a string array obtained from splitting command with
     *             the whitespace regex.
     * @throws CustomExceptions.ToBeforeFromException if the /to command comes
     *                                                before the /from command.
     * @throws CustomExceptions.EventExceptionForFromTo if the start and end time
     *                                                  of the event fails to parse.
     * @throws CustomExceptions.NamelessTaskException if the name in the command
     *                                                cannot be parsed.
     * @throws CustomExceptions.UnrecognizableDateException if the parser throws
     *                                                      a DateTimeParseException.
     */
    public Event(String[] info) throws CustomExceptions {
        int index = 1;
        String s = "";
        while ((index < info.length) && !info[index].equals("/from")) {
            if (info[index].equals("/to")) {
                throw new CustomExceptions.ToBeforeFromException("Wrong input, /to should be after /from");
            }
            this.name += info[index] + " ";
            index++;
        }
        if (index < info.length) {
            for (int i = index; i < info.length; i++) {
                s += info[i] + " ";
            }
            try {
                this.start = Parser.parseDtString(s.split("/from|/to")[1].trim());
                this.end = Parser.parseDtString(s.split("/from|/to")[2].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CustomExceptions.EventExceptionForFromTo("");
            } catch (DateTimeParseException e) {
                throw new CustomExceptions.UnrecognizableDateException(
                        "Date format is unrecognizable, try dd/mm/yy hhmm");
            }
        }

        this.name = this.name.trim();

        if (this.name.equals("")) {
            throw new CustomExceptions.NamelessTaskException("Missing duke.item.Event Name");
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
                + "       " + this.toString()
                + "\n     Now you have " + num + " tasks in the list.";
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
    public String toString() {
        return "[E]["
                + printChecked(this.isDone) + "] " + this.name + " " + "(from: "
                + this.start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + " to: "
                + this.end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + ")";
    }
}
