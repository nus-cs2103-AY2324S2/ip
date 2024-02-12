package rick.tasks;

import rick.RickException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline implements Item {
    public String name;
    public String status;
    public LocalDateTime ddl;

    public Deadline(String name, String status, String ddl) throws RickException {
        try {
            if (name.isBlank()) {
                throw new RickException("Nothing is due!");
            }
            if (ddl.isBlank()) {
                throw new RickException("due when?");
            }
            this.name = name;
            this.ddl = ddl.length() == 10 ? LocalDateTime.parse(ddl + "T00:00:00")
                    : LocalDateTime.parse(ddl);
            this.status = status;
        } catch (Exception e) {
            throw new RickException("Something wrong with your input! " +
                    "Follow 'deadline [ddl] /by yyyy-mm-ddTHH:mm:ss'");
        }
    }
    @Override
    public String toString(){
        return "[D]" + this.status + " " + this.name + " (by: " +
                this.ddl.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) + ")";
    }
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
    public String store() {
        return "D|" + this.status + "|" + this.name + "|" + this.ddl;
    }
}
