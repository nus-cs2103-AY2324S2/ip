package parser;

import exception.MalformedUserInputException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    // Solution adapted from: https://stackoverflow.com/questions/22463062/how-can-i-parse-format-dates-with-localdatetime-java-8
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate localDate;

    public DateTimeParser(String dateTime) throws MalformedUserInputException {
        try {
            // Possible formats
            // 12 May
            // 12 May HH:mm
            // 12 May 2023
            // 12 May 2024 11:22
            // Monday
            // Monday 2 PM
            // Monday 2:00 PM
            localDate = LocalDate.parse(dateTime, DATE_INPUT_FORMAT);

        } catch (DateTimeException dateTimeException) {
            throw new MalformedUserInputException("Invalid date time format. \n" +
                    "Please enter in format 2024-01-01.");
        }
    }

    public String toStorageString() {
        return this.localDate.format(DATE_INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return this.localDate.format(DATE_OUTPUT_FORMAT);
    }
}
