package Gops;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {
    protected String endDateString;
    String reformattedEndDateString;
    protected LocalDate endDate;
    protected boolean todoStatus = false;
    public Deadline(String todoDescription, String endDateString) {
        super(todoDescription);
        this.endDateString = endDateString.trim();
        if (endDateString.replaceAll("\\s", "").length() != 9) {
            dateFormatter(endDateString);
        } else {
            reformattedEndDateString = endDateString.trim();
        }
    }

    private void dateFormatter(String dateString) {
        endDate = LocalDate.parse(dateString.trim());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        reformattedEndDateString = endDate.format(dateFormatter);
    }

    @Override
    public String stringPrinter() {
        return "D " + "| " + this.doneOrNot() + " | " + itemDescription + " | by: " + reformattedEndDateString;
    }

    @Override
    public void Printer() {
        System.out.println(stringPrinter());
    }

}