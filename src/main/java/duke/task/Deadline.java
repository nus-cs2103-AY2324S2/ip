package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of Task. Each Deadline comes with a description and a deadline.
 */

public class Deadline implements Task {

    protected String desc;
    protected LocalDate date;
    protected boolean isMarked;
    protected String type;

    public Deadline(String desc, LocalDate date){
        this.desc = desc;
        this.date = date;
        isMarked = false;
        type = "D";
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "by: " + date.format(pattern);
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public String getCheck() {
        return isMarked ? "X" : " ";
    }

    public void setCheck(boolean x) {
        isMarked = x;
    }
    public String getType() {
        return type;
    }

    public String toSave() {
        String temp = isMarked ? "1" : "0";
        return type + " | " + temp + " | " + getDesc() + " | " + date.toString() + "\n";
    }

    @Override
    public String toString() {

        return ("[" + type + "][" + getCheck() + "] " + desc + " (" + getDate() + ")");
    }
}
