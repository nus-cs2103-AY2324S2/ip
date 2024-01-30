import YapchitExceptions.InvalidDetailException;
import YapchitExceptions.InvalidKeywordException;
import YapchitExceptions.YapchitException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser(){ }

    public Yapchit.Operations parseInputOperation(String input) throws YapchitException{
        String[] parts = this.parseInputParts(input);

        Yapchit.Operations k;
        try{
            k = Yapchit.Operations.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidKeywordException("You have entered an invalid keyword. " +
                    "Valid keywords are ['mark', 'unmark', 'deadline', 'todo', 'event', 'bye', 'list', 'delete']");
        }

        return k;
    }

    public String[] parseInputParts(String input){
        return input.split(" ");
    }

    public LocalDate parseTimestamp(String timestamp) throws InvalidDetailException {
        try {
            LocalDate d = LocalDate.parse(timestamp);
        } catch (DateTimeParseException e){
            throw new InvalidDetailException("Please enter date in yyyy-mm-dd format");
        }
        return LocalDate.parse(timestamp);
    }


}
