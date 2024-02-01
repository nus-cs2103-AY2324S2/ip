package Gluti.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline subclass that is a child of Task class
 */

public class Deadline extends Task {

    protected String by;

    /**
     * Initializes a Deadline instance
     * @param description the name of the Deadline
     * @param by the due date of the Tasks
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        validDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by +")";
    }

    /**
     * Checks if the input is a valid date and perform the relevant formatting if valid
     * @param by the due date input
     */
    private void validDate(String by) {
        String temp = by.trim();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(temp, inputFormatter);
            String formattedDate = parsedDate.format(outputFormatter);
            this.by = " " +formattedDate;
//            System.out.println("Input Date: " + temp);
//            System.out.println("Formatted Date: " + formattedDate);
        } catch (Exception e) {
            // Handle the parsing exception (invalid format)
            //System.out.println("Error: Invalid date format");
        }
        }
}
