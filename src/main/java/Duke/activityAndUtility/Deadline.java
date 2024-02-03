package Duke.activityAndUtility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Deadline implements Activity {
    List<String> act;
    LocalDate date;
    LocalTime time;

    public Deadline(String status, String name, String dateAndTime) {
        act = new ArrayList<>();
        act.add(status); // Status
        act.add(name); // Task name
        act.add(dateAndTime);
        LocalDate date = DateTimeFormat.getDate(dateAndTime);
        LocalTime time = DateTimeFormat.getTime(dateAndTime);
        this.date = date;
        this.time = time;
    }


    @Override
    public void printActivity() {
        if (date != null && time != null) {
            String dateOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String timeOutput = time.format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.format("\t\t [D][%s]%s(by: %s, %s)%n", act.get(0), act.get(1), dateOutput, timeOutput);
        } else {
            System.out.format("\t\t [D][%s]%s(by: %s)%n", act.get(0), act.get(1), act.get(2));
        }
    }

    @Override
    public String getName() {
        return act.get(1);
    }

    @Override
    public void mark(String input) {
        if (Objects.equals(input, "mark")) {
            act.set(0, "√");
        } else if (Objects.equals(input, "unmark")) {
            act.set(0, "X");
        }
        System.out.format("\t%sed:%n", input);
        printActivity();
    }
}
