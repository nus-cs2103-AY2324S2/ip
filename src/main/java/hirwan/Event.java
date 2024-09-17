package hirwan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String input;
    Tasklist tasks;

    /**
     * the event constructor to create an event object
     * @param input the string input to be translated to the command
     * @param tasks the task list to be edited
     */
    public Event(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * the getMessage method that returns the string to be printed to the user
     * @return the string to be printed to the user
     */
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

    /**
     * the updateDate method that updates the data in the external text file when an event task is created
     */
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
