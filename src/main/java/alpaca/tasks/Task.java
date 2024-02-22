package alpaca.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {
    private String name;
    private boolean isDone;
    private String type;
    private ArrayList<LocalDate> dates;

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
        Pattern pattern = Pattern.compile(
                "\\d{4}-\\d{1,2}-\\d{1,2}" + // matches yyyy-mm-dd
                "|\\d{1,2}-\\d{1,2}-\\d{4}" + // matches dd-mm-yyyy
                "|\\d{4}\\/\\d{1,2}\\/\\d{1,2}" + // matches yyyy/mm/dd
                "|\\d{1,2}\\/\\d{1,2}\\/\\d{4}"); // matches dd/mm/yyyy
        Matcher matcher = pattern.matcher(this.name);
        while (matcher.find()) {
            try {
                String dateStr = matcher.group();
                String dateMod = dateStr.replaceAll("\\/", "-");
                dateMod = dateMod.replaceFirst("(^|[^\\d])(\\d)($|[^\\d])", "$10$2$3");
                dateMod = dateMod.replaceFirst("(^|[^\\d])(\\d)($|[^\\d])", "$10$2$3");
                dateMod = dateMod.replaceAll("(\\d{2})-(\\d{2})-(\\d{4})", "$3-$2-$1");
                LocalDate date = LocalDate.parse(dateMod);
                this.dates.add(date);
                this.name = this.name.replaceFirst(dateStr, "~");
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
        }
        ;
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
