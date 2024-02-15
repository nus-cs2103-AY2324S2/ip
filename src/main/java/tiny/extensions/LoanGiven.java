package tiny.extensions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tiny.exceptions.TinyException;

public class LoanGiven {
    protected String name;
    protected Double amount;
    protected LocalDate dueDate;

    public LoanGiven(String name, Double amount, String dueDate) throws TinyException {
        this.name = name;
        this.amount = amount;

        this.dueDate = dateParser(dueDate);
    }


    //Need to edit error msg
    public LocalDate dateParser(String date) throws TinyException {
        int year = 0;
        int month = 0;
        int day = 0;
        String errorMsg = "Please ensure that you are using the format loan given /to <name> /for "
                + "<amount> /on <yyyy-MM-dd>. eg. loan given /to Duke /for 10 /on 2024-01-29";
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
        return "LG | " + name + " | " + amount + " | " + dateSaveFormat(dueDate);
    }  
    
    @Override
    public String toString() {
        return "Name: " + name + " | Amount: " + amount + " |  Date: " + dateToString(dueDate);
    }    
}
