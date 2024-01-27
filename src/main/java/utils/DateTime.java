package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.ConvoBotException;

public class DateTime {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public static String dateToString(LocalDate d) {
        return d.format(displayFormatter);
    }

    public static String dateToFile(LocalDate d) {
        return d.format(inputFormatter);
    }

    public static LocalDate stringToDate(String s) throws ConvoBotException {
        LocalDate d;
        try {
            d = LocalDate.parse(s, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new ConvoBotException("Invalid input. Wrong date format.");
        }
        return d;
    }
}
