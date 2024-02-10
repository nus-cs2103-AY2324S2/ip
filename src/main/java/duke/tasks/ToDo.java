package tasks;

import java.time.format.DateTimeFormatter;

public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    public ToDo (String description, Boolean status) {
        super(description, status);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return "T | " + super.convertToFileFormat(storeFormatter);
    }
}
