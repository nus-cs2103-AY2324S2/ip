import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String dueText;
    protected LocalDate dueDate;

    public Deadline() { type = "Deadline"; name = "deadline"; dueDate = LocalDate.now(); isMarked = false; }
    public Deadline(String _s, boolean _flag) {
        // Check for invalid params
        if (!_s.contains(" /by ")) {
            throw new InvalidParamException("Invalid param for deadline", null);
        }

        type = "Deadline";
        name = _s.substring(0, _s.indexOf(" /by "));
        dueText = _s.substring(_s.indexOf(" /by ") + 5);
        try {
            dueDate = LocalDate.parse(dueText);
        } catch (DateTimeParseException e) {
            dueDate = null;
        }
        isMarked = _flag;
    } 

    @Override
    public String ToString() { 
        String s = "[D][";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + name + 
            " (by: " + 
            (dueDate != null ? dueDate.toString() : dueText)
             + ")";

        if (dueDate != null) {
            Period p = Period.between(LocalDate.now(), dueDate);
            int i = p.getDays();
            if (p.getMonths() >= 1) {
                s += " {a month or more left}";
            } else if (i >= 2) {
                s += " {" + i + " days left}";
            } else if (i == 1) {
                s += " {" + i + " day left}";
            } else if (i == 0) {
                s += " {TODAY}";
            } else {
                s += " {ALREADY OVER}";
            }
        }

        return s;
    }

    @Override
    public String GetFileFormatParam() {
        return name + " /by " + dueText;
    }
}