package hirwan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String input;
    Tasklist tasks;
    public Event(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }
    @Override
    public String getMessage() {
        try {
        String output = "";

        String delimiterstart = " /from";
        String delimiterend = " /to";
        int indexStart = input.indexOf(delimiterstart);
        int indexEnd = input.indexOf(delimiterend);

        assert indexStart != -1;
        assert indexEnd != -1;

        String start = input.substring(indexStart + 7, indexEnd);
        String end = input.substring(indexEnd + 5);
        String item = input.substring(6, indexStart);

        LocalDateTime startDate = Hirwan.translateDate(start);
        LocalDateTime endDate = Hirwan.translateDate(end);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

        output = "Got it. I've added this task:\n  " + "[E][ ] " + item + " (from: "
                + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")";
        output = output + "\nNow you have " + tasks.size() + " tasks in the list.";
        return output;
        } catch (StringIndexOutOfBoundsException e) {
            return "Error: Please enter a description or date for your event to command";
        }
    }

    @Override
    public void updateData() {
        String delimiterstart = " /from";
        String delimiterend = " /to";
        int indexStart = input.indexOf(delimiterstart);
        int indexEnd = input.indexOf(delimiterend);

        assert indexStart != -1;
        assert indexEnd != -1;

        String start = input.substring(indexStart + 7, indexEnd);
        String end = input.substring(indexEnd + 5);
        String item = input.substring(6, indexStart);

        LocalDateTime startDate = Hirwan.translateDate(start);
        LocalDateTime endDate = Hirwan.translateDate(end);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

        tasks.add(". " + "[E][ ] " + item + " (from: " + startDate.format(formatter) + " to: "
                + endDate.format(formatter) + ")");
        Storage.writeTask(this.tasks.getList());
    }
}
