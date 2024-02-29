package goblin.calendar;

import goblin.exception.OrkException;
import java.time.Month;

public class Date {
    protected int day;
    protected Month month;
    protected int year;
    protected String unprocessed;

    public Date(String unprocessed) throws OrkException {
        this.unprocessed = unprocessed;
        processDate();
    }
    public void processDate() throws OrkException {
        if (unprocessed.isEmpty()) {
            return;
        }
        String[] dateSplit = unprocessed.split("/");
        if (dateSplit.length < 3) {
            throw new OrkException("Is the format of date/month/year so difficult for you to understand?");
        }

        int monthInt = Integer.parseInt(dateSplit[1]);
        if (monthInt >= 1 && monthInt <= 12) {
            this.month = Month.of(monthInt);
        } else {
            throw new OrkException("There's no such month!");
        }

        this.year = Integer.parseInt(dateSplit[2]);
        int day = Integer.parseInt((dateSplit[0]));
        if (isValidDay(day, monthInt, year)) {
            this.day = day;
        } else {
            throw new OrkException("There's no such day!");
        }
    }

    public boolean isValidDay (int day, int monthInt, int year) {
        switch(monthInt) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return (day >= 1 && day <= 31);
            case 4:
            case 6:
            case 9:
            case 11:
                return (day >= 1 && day <= 30);
            case 2:
                if (isLeapYear(year) && day >= 1 && day <= 29) {
                    return true;
                } else {
                    return (day >= 1 && day <= 28);
                }
            default:
                return false;
        }
    }

    public boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return (year % 4 == 0);
        }
    }

    @Override
    public String toString() {
        if (month == null) {
            return "";
        }
        if (day % 10 == 1 && day != 11) {
            return day + "st of " + month + " " + year;
        } else if (day % 10 == 2 && day != 12) {
            return day + "nd of " + month + " " + year;
        } else {
            return day + "th of " + month + " " + year;
        }
    }
}
