package tiny.extensions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tiny.exceptions.TinyException;

public class Expense {
    protected String description;
    protected Double amount;
    protected LocalDate transactionDate;

    public Expense(String description, double amount, String transactionDate) throws TinyException {
        this.description = description;
        this.amount = amount;
        this.transactionDate = dateParser(transactionDate);
    }

    public LocalDate dateParser(String date) throws TinyException {
        int year = 0;
        int month = 0;
        int day = 0;
        String errorMsg = "Please ensure that you are using the format expense <description> /for "
                + "<amount> /on <yyyy-MM-dd>. eg. expense chicken rice /for 3.50 /on 2024-01-29";
        // Processes the date
        try {
            String[] dateSplit = date.split("-");
            assert dateSplit.length == 3;
            year = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            day = Integer.parseInt(dateSplit[2]);
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }
    }    

    public String dateSaveFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    private String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    public String formatToSave() {
        return "EX | " + description + " | " + amount + " | " + dateSaveFormat(transactionDate);
    }

    @Override
    public String toString() {
        return "Description: " + description + " | Amount: " + amount + " |  Date: " + dateToString(transactionDate);
    }
}
