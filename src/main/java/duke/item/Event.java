package duke.item;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import duke.CustomExceptions;
import duke.Parser;

public class Event implements Item, Serializable {
    private boolean isDone = false;
    private String name = "";
    private LocalDateTime start = LocalDateTime.now();
    private LocalDateTime end = LocalDateTime.now();
    public Event(String[] info) throws CustomExceptions {
        int index = 1;
        String s = "";
        while ((index < info.length) && !info[index].equals("/from")) {
            if (info[index].equals("/to")) {
                throw new CustomExceptions.toBeforeFromException("Wrong input, /to should be after /from");
            }
            this.name += info[index] + " ";
            index++;
        }
        if (index < info.length) {
            for (int i = index; i < info.length; i++) {
                s += info[i] + " ";
            }
            try {
                this.start = Parser.parseDTString(s.split("/from|/to")[1].trim());
                this.end = Parser.parseDTString(s.split("/from|/to")[2].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CustomExceptions.eventExceptionForFromTo("Unable to parse /from /to strings");
            } catch (DateTimeParseException e) {
                throw new CustomExceptions.unrecognizableDateException("Date format is unrecognizable, try dd/mm/yy hhmm");
            }
        }

        this.name = this.name.trim();

        if (this.name.equals("")) {
            throw new CustomExceptions.namelessTaskException("Missing duke.item.Event Name");
        }
    }

    @Override
    public String doneMessage() {
        return "Nice! I've marked this task as done:\n     " +
                this.toString();
    }

    @Override
    public String undoneMessage() {
        return "OK, I've marked this task as not done yet:\n     " +
                this.toString();
    }

    @Override
    public String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    @Override
    public String addMessage(int num) {
        return "Got it. I've added this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
    }

    @Override
    public String removeMessage(int num) {
        return "Noted. I've removed this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
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
                + printChecked(this.isDone) + "] " + this.name + " " + "(from: " +
                this.start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + " to: " +
                this.end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + ")";
    }
}
