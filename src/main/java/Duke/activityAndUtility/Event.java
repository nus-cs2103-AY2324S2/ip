package Duke.activityAndUtility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event implements Activity {
    List<String> act;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;


    public Event(String status, String name, String startDateAndTime, String endDateAndTime) {
        act = new ArrayList<>();
        act.add(status); // Status
        act.add(name); // Event name
        act.add(startDateAndTime);
        act.add(endDateAndTime);
        LocalDate startDate = DateTimeFormat.getDate(startDateAndTime);
        LocalTime startTime = DateTimeFormat.getTime(startDateAndTime);
        LocalDate endDate = DateTimeFormat.getDate(endDateAndTime);
        LocalTime endTime = DateTimeFormat.getTime(endDateAndTime);
        if (startDate != null && endDate != null) {
            if (startDate.isAfter(endDate)) {
                throw new RuntimeException("Finish date ahead of start date");
            } else if (startDate.isEqual(endDate)) {
                if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
                    throw new RuntimeException ("Finish time ahead of start time");
                }
            }
        }
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public void printActivity() {
        if (startDate != null && startTime != null && endDate != null && endTime != null) {
            String startDateOutput = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String startTimeOutput = startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            String endDateOutput = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String endTimeOutput = endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.format("\t\t [E][%s]%s(from: %s %s to: %s %s)%n",
                    act.get(0), act.get(1), startDateOutput, startTimeOutput, endDateOutput, endTimeOutput);
        } else {
            System.out.format("\t\t [E][%s]%s(%s %s)%n",
                    act.get(0), act.get(1), act.get(2), act.get(3));
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
