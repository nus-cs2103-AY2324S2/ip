package CinnamoRoll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Parser {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String[] parse(String str) {
        String[] output = str.split(" ", 2);
        output[0] = output[0].toUpperCase();
        return output;
    }

    Task parse_tasks(String[] arr) throws CinnamoTimeException {
        try {
            String identifier = arr[0];
            if (identifier.equals("TODO")) {
                return new Todos(arr[1]);
            } else if (identifier.equals("DEADLINE")) {
                String[] schedule = arr[1].split(" /by ", 2);
                return new Deadlines(schedule[0], LocalDateTime.parse(schedule[1], this.format));
            } else {
                String[] schedule = arr[1].split(" /from | /to ");
                return new Events(schedule[0], LocalDateTime.parse(schedule[1], this.format),
                        LocalDateTime.parse(schedule[2], this.format));
            }
        } catch (DateTimeParseException dtp) {
            throw new CinnamoTimeException();
        }
    }
}
