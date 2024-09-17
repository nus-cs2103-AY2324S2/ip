package hirwan;

import java.util.List;

public class Listcommand extends Command {
    Tasklist tasks;

    /**
     * the listCommand constructor
     * @param tasks the tasks list of tasks
     */
    public Listcommand(Tasklist tasks) {
        this.tasks = tasks;
    }

    /**
     * getmessage method which returns the text to print to the user when the command is called
     * @return the string output that is returned to the user
     */
    public String getMessage() {
        String output = "";
        int j = 1;
        for (String i : this.tasks.getList()) {
            output += j + i + "\n";
            j++;
        }
        return output;
    }
}
