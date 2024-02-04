package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.exceptions.DukeException;

public class DeadlineTask extends Task {

    protected LocalDateTime end_time;
    public DeadlineTask(String name, LocalDateTime end_time, String file_format) throws DukeException {
        super(name, Task.Type.D, file_format);
        this.end_time = end_time;

        if (this.name.isBlank()) {
            String error_message = "\tInvalid deadline description!\n\tEx: deadline return book /by Sunday\n";
            throw new DukeException(error_message);
        }
    }

    @Override
    public String toString() {
        String output;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (done) {
            output = "[" + this.type + "]" + "[X] " + this.name + " (by: " + this.end_time.format(formatter) + ")\n";
        } else {
            output = "[" + this.type + "]" + "[ ] " + this.name + " (by: " + this.end_time.format(formatter) + ")\n";
        }
        return output;
    }
}
