package Gluti.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        validDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by +")";
    }

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
