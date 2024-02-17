package dude.Tasks;

import dude.Exceptions.InvalidArgumentException;
import dude.Exceptions.InvalidDescriptionException;
import dude.Exceptions.InvalidFormatException;
import dude.Utils.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public static Deadline from(String s) throws InvalidFormatException, InvalidDescriptionException, InvalidArgumentException {
        //Expects a string in the format "deadline <description> /by <deadline_date>"

        //get rid of the command
        String rest = utils.discardFirstWord(s.trim()).trim();

        String[] arr = rest.split(" ");

        int by_occurences = utils.countOccurrences(arr, "/by");

        if (by_occurences == 0 || by_occurences > 1){
            throw new InvalidFormatException("deadline", "format: deadline <description> /by <deadline date>. Provide one and only one '/by'.");
        }

        //they will not be -1 as I have already checked for their occurences
        int by_index = utils.findIndex(arr, "/by");

        //description is from 0 to by_index
        String description = "";
        for (int i = 0; i < by_index; i++){
            description += arr[i] + " ";
        }
        description = description.trim();
        if (description.isEmpty()){
            throw new InvalidDescriptionException("The description of a deadline cannot be empty.");
        }

        String by = "";
        for (int i = by_index+1; i < arr.length; i++){
            by += arr[i] + " ";
        }
        by = by.trim();
        if (by.isEmpty()){
            throw new InvalidArgumentException("The 'by' of a deadline cannot be empty. Follow this format: deadline <description> /by <deadline date time>");
        }

        try{
            LocalDateTime dt = parseDate(by);
            return new Deadline(description, dt);
        }catch (DateTimeParseException e){
            throw new InvalidFormatException("Invalid date format after '/by'. Use d/M/yyyy or d/M/yyy H:m in 24-hour format");
        }
    }

    private final LocalDateTime deadline_date;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadline_date = by;
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(deadline_date) + ")";
    }

    public LocalDateTime getBy() {
        return deadline_date;
    }
}
