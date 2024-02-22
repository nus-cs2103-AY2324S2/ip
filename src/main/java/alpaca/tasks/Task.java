package alpaca.tasks;

import java.text.ParsePosition;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {
    private String name;
    private boolean isDone;
    private String type;
    private ArrayList<LocalDate> dates;
    private final static String[][] DATE_PATTERNS = {
        { "\\\\d{4}-\\\\d{1,2}-\\\\d{1,2}", "yyyy-M-d" },
        { "\\d{1,2}-\\d{1,2}-\\d{4}", "d-M-yyyy" },
        { "\\d{4}\\/\\d{2}\\/\\d{1,2}", "yyyy/M/d" },
        { "\\d{1,2}\\/\\d{1,2}\\/\\d{4}", "d/M/yyyy" },
        { "\\d{1,2}-\\[a-zA-Z]{3}-\\d{4}", "d-LLL-yyyy" },
        { "\\d{1,2}\\/\\[a-zA-Z]{3}\\/\\d{4}", "d/LLL/yyyy" },
        { "\\d{1,2} [a-zA-Z]{3} \\d{4}", "d LLL yyyy"},
    };
    private final static String[][] DAY_PATTERNS = {
            { "monday", "mon" },
            { "tuesday", "tues", "tue" },
            { "wednesday", "weds", "wed" },
            { "thursday", "thurs", "thur", "thu" },
            { "friday", "fri" },
            { "saturday", "sat" },
            { "sunday", "sun" },
    };

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
        processDates();
        processParams();
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return type;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    protected void setType(String type) {
        this.type = type;
    }

    private void processDates() {
        this.dates = new ArrayList<>();
        for (String[] datePattern : DATE_PATTERNS) {
            Pattern pattern = Pattern.compile(datePattern[0]);
            Matcher matcher = pattern.matcher(this.name);
            while (matcher.find()) {
                try {
                    String dateStr = matcher.group();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern[1]);
                    LocalDate date = LocalDate.from(formatter.parse(dateStr, new ParsePosition(0)));
                    this.dates.add(date);
                    this.name = this.name.replaceFirst(dateStr, "~");
                } catch (DateTimeParseException e) {
                    System.out.println(e);
                }
            }
        }
        for (int i = 0; i < DAY_PATTERNS.length; i++) {
            for (String dayPattern: DAY_PATTERNS[i]) {
                Pattern pattern = Pattern.compile("(\\s|^)("+ dayPattern + ")(\\s|$)", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(this.name);
                while (matcher.find()) {
                    LocalDate date = LocalDate.now();
                    switch (i) {
                        case 0:
                            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                            break;
                        case 1:
                            date = date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                            break;
                        case 2:
                            date = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                            break;
                        case 3:
                            date = date.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                            break;
                        case 4:
                            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                            break;
                        case 5:
                            date = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                            break;
                        case 6:
                            date = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                            break;
                        default:
                            break;
                    }
                    String dateStr = matcher.group(2);
                    this.dates.add(date);
                    this.name = this.name.replaceFirst(dateStr, "~");
                }
            }
        }
    }

    private void processParams() {
        Pattern pattern = Pattern.compile("^[^\\/]+");
        Matcher matcher = pattern.matcher(this.name);
        if (!matcher.find()) {
            return;
        }
        String result = matcher.group();
        String tmp = this.name.replaceFirst("^[^/]+", "");
        if (tmp.equals("")) {
            return;
        }
        tmp = tmp.replaceAll("(/)([a-zA-Z]+)(\\s|$)", "$2:$3");
        this.name = result + "(" + tmp + ")";
    }

    public String toString() {
        String type = "[" + this.getType() + "]";
        String mark = "[" + (this.isDone() ? "X" : " ") + "]";
        String content = this.name;
        for (LocalDate date : dates) {
            content = content.replaceFirst(
                    "~", date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
        return type + mark + " " + content;
    }

    public String toStringAlt() {
        String content = this.name;
        for (LocalDate date : dates) {
            content = content.replaceFirst("~", date.toString());
        }
        return this.getType() + " | " + (this.isDone ? "1" : "0") + " | " + content;
    }
}
