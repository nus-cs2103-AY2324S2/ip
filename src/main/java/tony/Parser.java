package tony;

import java.time.LocalDateTime;
import java.util.Arrays;


public class Parser {

    public String parseCommand(String input) {
        String[] words = input.split(" ");
        return words[0].toLowerCase(); // Convert the command to lowercase for case-insensitive matching
    }

    public String parseDescription(String input) {
        String[] words = input.split(" ");
        return String.join(" ", Arrays.copyOfRange(words, 1, words.length));
    }

    public String[] parseTasksWithDate(String input) {
        String[] words = input.split(" ");
        String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        String[] parts = result.split("/");
        return parts;
    }
    public static LocalDateTime parseDate(String date) {
        String[] dateTimeSplit = date.split("T");
        String dateString = dateTimeSplit[0];
        String timeString = dateTimeSplit[1];
        String[] parts = dateString.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        String[] timeParts = timeString.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        return LocalDateTime.of(year,month,day, hour, minutes);
    }
}
