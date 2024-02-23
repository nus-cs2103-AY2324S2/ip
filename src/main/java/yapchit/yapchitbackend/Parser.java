package yapchit.yapchitbackend;

import yapchit.yapchitui.Yapchit;
import yapchit.yapchitexceptions.InvalidDetailException;
import yapchit.yapchitexceptions.InvalidKeywordException;
import yapchit.yapchitexceptions.YapchitException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser class is responsible for parsing input from the user into formats that are easier
 * for the program to work with.
 */
public class Parser {

    /**
     * constructs new Parser object
     */
    public Parser(){
    }

    /**
     * Accepts input and converts it into one of the existing, handled operations listed in
     * the Operations enum.
     *
     * @param input user input to be parsed
     * @return YapchitBackend.Operations object representing the operation to be performed
     * @throws YapchitException if the user input cannot be parsed into one of the existing operations.
     */
    public YapchitBackend.Operations parseInputOperation(String input) throws YapchitException {
        String[] parts = this.parseInputParts(input);

        YapchitBackend.Operations k;
        try {
            k = YapchitBackend.Operations.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidKeywordException("You have entered an invalid keyword. "
                    + "Valid keywords are ['mark', 'unmark', 'deadline', 'todo', 'event',"
                    + " 'bye', 'list', 'delete', 'update']"
            );
        }

        return k;
    }

    /**
     * Wrapper over input.split() method to split string input
     *
     * @param input to be parsed into parts
     * @return String[] of parsed input
     */
    public String[] parseInputParts(String input) {
        return input.split(" ");
    }

    /**
     * Parses time from yyyy-mm-dd format into Java date object
     *
     * @param timestamp date to parse
     * @return LocalDate object representing the date
     * @throws InvalidDetailException if timestamp is invalid or unable to be parsed
     */
    public LocalDate parseTimestamp(String timestamp) throws InvalidDetailException {
        try {
            LocalDate d = LocalDate.parse(timestamp);
        } catch (DateTimeParseException e) {
            throw new InvalidDetailException("Please enter date in yyyy-mm-dd format");
        }
        return LocalDate.parse(timestamp);
    }
}
