package hirwan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String input;
    Tasklist tasks;

    /**
     * deadline constructor to construct a deadline object
     * @param input the command to be decoded into the task
     * @param tasks the tasklist of all tasks
     */
    public Deadline(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * getMessage method that returns the output of the deadline command
     * @return the string to be printed to the user
     */
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

    /**
     * the updateData method that updates the data in the external text file
     */
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
