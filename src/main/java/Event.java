import java.util.*;
import java.io.*;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String s, String from, String to) {
        super(s);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + (super.getStatus() ? "X" : " ") + "] " +
                super.getTask() + "(from: " + from + "to: " + to + ")";

    }
}
