package hirwan;

import java.util.List;

public class Listcommand extends Command {
    Tasklist tasks;
    public Listcommand(Tasklist tasks) {
        this.tasks = tasks;
    }
    public String getMessage() {
        String output = "";
        for (String i : this.tasks.getList()) {
            output += i + "\n";
        }
        return output;
    }
}
