package tiny.extensions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tiny.exceptions.TinyException;

/**
 * Represents a taken loan.
 */
public class LoanTaken {
    protected String name;
    protected Double amount;
    protected LocalDate dueDate;

    /**
     * Initializes LoanTaken.
     *
     * @param name    Name of the person.
     * @param amount  Amount of the loan.
     * @param dueDate Due date of the loan.
     */
    public LoanTaken(String name, Double amount, String dueDate) throws TinyException {
        this.name = name;
        this.amount = amount;
        this.dueDate = dateParser(dueDate);
    }

    private LocalDate dateParser(String date) throws TinyException {
        int year = 0;
        int month = 0;
        int day = 0;
        String errorMsg = "Please ensure that you are using the format loan taken /from <name> /for "
                + "<amount> /due <yyyy-MM-dd>. eg. loan taken /from Duke /for 10 /due 2024-01-29";
        String invalidDateErrorMessage = "Please ensure that the date is valid. eg. 2024-01-29";
        // Processes the date
        try {
            String[] dateSplit = date.split("-");
            assert dateSplit.length == 3;
            year = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            day = Integer.parseInt(dateSplit[2]);
            if (!isValidDate(month, day)) {
                throw new TinyException(invalidDateErrorMessage);
            }
            return LocalDate.of(year, month, day);
        } catch (TinyException e) {
            throw e;
        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }
    }

    private Boolean isValidDate(int month, int day) {
        if (month > 12 || month < 1) {
            return false;
        }

        if (month == 2) {
            return day <= 29;
        }

        int[] thirtyDayMonth = new int[] { 4, 6, 9, 11 };
        int[] thirtyOneDayMonth = new int[] { 1, 3, 5, 7, 8, 10, 12 };

        for (int i = 0; i < thirtyOneDayMonth.length; i++) {
            if (thirtyDayMonth[i] == month) {
                return day <= 30;
            }
        }

        for (int i = 0; i < thirtyOneDayMonth.length; i++) {
            if (thirtyOneDayMonth[i] == month) {
                return day <= 31;
            }
        }
        return true;
    }

    private String dateSaveFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    private String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    public String formatToSave() {
        return "LT | " + name + " | " + amount + " | " + dateSaveFormat(dueDate);
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Amount: " + amount + " |  Date: " + dateToString(dueDate);
    }

}
