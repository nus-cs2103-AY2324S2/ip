package Gluti.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

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
        adjustByDate(by_date);
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

    private void adjustByDate(String inputDayOfWeek) {
        DayOfWeek[] daysOfWeek = {
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY
        };

        String abbreviatedInputDay = inputDayOfWeek.substring(0, 4).toLowerCase();
        for (DayOfWeek dayOfWeek : daysOfWeek) {

            String abbreviatedDayOfWeek = dayOfWeek.toString().substring(0, 3).toLowerCase();
            if (abbreviatedInputDay.contains(abbreviatedDayOfWeek)) {
                LocalDate currentDate = LocalDate.now();
                DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();
                DayOfWeek targetDayOfWeek = dayOfWeek;

                // Calculate the difference in days to the next occurrence of the target day of the week
                int daysToAdd = (dayOfWeek.getValue() - currentDayOfWeek.getValue() + 7) % 7;
                if (currentDayOfWeek == targetDayOfWeek) {
                    daysToAdd += 7;
                }
                // Set the by_date to the next occurrence of the target day of the week
                LocalDate nextDate = currentDate.plusDays(daysToAdd);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                this.by_date = nextDate.format(formatter);
                return;
            }
        }
    }
}
