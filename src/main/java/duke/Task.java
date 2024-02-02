package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class Task {
    protected final String name;
    protected boolean done;
    
    public static final DateTimeFormatter INPUT_TIME_FORMATTER 
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_TIME_FORMATTER 
            = DateTimeFormatter.ofPattern("hh:mm a, M-dd-yyyy");

    public Task(String name) {
        this.name = name;
        this.done = false;
    }
    
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }

    public String describe() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }
    
    public String toStorageString() {
        return String.format("%s,%s", this.name, this.done ? "T" : "F");
    }
    
    
    public static Task fromStorageString(String str) throws DukeException {
        Scanner sc = new Scanner(str);
        String typeStr, nameStr, doneStr;
        Task t;
        
        sc.useDelimiter(",");
        if (!sc.hasNext()) {
            throw new DukeException("expected a type identifier, but none was given");
        }
        typeStr = sc.next();

        if (!sc.hasNext()) {
            throw new DukeException("expected a name, but none was given");
        }
        nameStr = sc.next();
        
        if (!sc.hasNext()) {
            throw new DukeException("expected an done identifier, but none was given");
        }
        doneStr = sc.next();
        
        switch (typeStr) {
        case "T":
            t = new ToDo(nameStr);
            break;
        case "D":
            {
                if (!sc.hasNext()) {
                    throw new DukeException("expected a deadline, but none was given");
                }
                String deadline = sc.next();
                t = new Deadline(nameStr, deadline);
            }
            break;
        case "E": 
            {
                if (!sc.hasNext()) {
                    throw new DukeException("expected a start date, but none was given");
                }
                String start = sc.next();
                
                if (!sc.hasNext()) {
                    throw new DukeException("expected an end date, but none was given");
                }
                String end = sc.next();
                
                t = new Event(nameStr, start, end);
            }
            break;
        default:
            throw new DukeException(String.format("unexpected type string %s", typeStr));
        }
        
        switch (doneStr) {
        case "T":
            t.done = true;
            break;
        case "F":
            t.done = false;
            break;
        default:
            throw new DukeException(String.format("unexpected done string %s", doneStr));
        }
        
        return t;
        
    }
}
