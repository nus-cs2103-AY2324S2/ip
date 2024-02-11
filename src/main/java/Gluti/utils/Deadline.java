package Gluti.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline subclass that is a child of Task class
 */

public class Deadline extends Task {

    protected String by_date;

    /**
     * Initializes a Deadline instance
     * @param description the name of the Deadline
     * @param by_date the due date of the Tasks
     */
    public Deadline(String description, String by_date) {
        super(description);
        this.by_date = by_date;
        validDate(by_date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by_date +")";
    }

    /**
     * Checks if the input is a valid date and perform the relevant formatting if valid
     * @param by_date the due date input
     */
    private void validDate(String by_date) {
        String temp = by_date.trim();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(temp, inputFormatter);
            String formattedDate = parsedDate.format(outputFormatter);
            this.by_date = " " +formattedDate;
        } catch (Exception e) {
            // Handle the parsing exception (invalid format)
            //System.out.println("Error: Invalid date format");
        }
        }
}
