package Lery.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String TYPE = "D";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate deadline;


    public Deadline(String description) {
        super(description.split("/")[0]);
        this.deadline = LocalDate.parse(description.split("/by ")[1], formatter);
    }

    public Deadline(String event, String extraInfo) {
        super(event);

        this.deadline = LocalDate.parse(extraInfo.replace('/','-'), formatter);;
    }

    public String getType() {
        return this.TYPE;
    }
    public String getExtraInfoShortened() {
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    public String getExtraInfo() {
        return "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}




