package hirwan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String input;
    Tasklist tasks;

    public Deadline(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    @Override
    public String getMessage() {
        try {
            String output = "";
            String delimiter = " /by";
            int index = this.input.indexOf(delimiter);

            assert index != -1;

            String day = this.input.substring(index + 5);
            String item = this.input.substring(9, index);

            LocalDateTime dayDate = Hirwan.translateDate(day);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");


            output = "Got it. I've added this task:\n  " + "[D][ ] " + item + " (by: "
                    + dayDate.format(formatter) + ")";
            output = output + "\nNow you have " + this.tasks.size() + " tasks in the list.";
            return output;
        } catch (StringIndexOutOfBoundsException e) {
            return "Error: Please enter a description or date for your deadline command";
        }
    }

    @Override
    public void updateData() {
        String delimiter = " /by";
        int index = this.input.indexOf(delimiter);

        String day = this.input.substring(index + 5);
        String item = this.input.substring(9, index);

        LocalDateTime dayDate = Hirwan.translateDate(day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

        this.tasks.add(". " + "[D][ ] " + item + " (by: " + dayDate.format(formatter) + ")");
        Storage.writeTask(this.tasks.getList());
    }
}
