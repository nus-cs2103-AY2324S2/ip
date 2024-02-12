import inputcommands.Command;
import snomexceptions.InvalidCommandException;
import SnomStorage.TaskStorage;
import snomparser.Parser;
import snomtasklist.TaskList;
import snomui.Ui;

/**
 * Snom class implements the working body of SnomBot.
 */
public class Snom {
    private Ui ui;
    private TaskStorage data;
    private TaskList lst;
    private Parser parser;

    public Snom() {
        this.ui = new Ui();
        this.data = new TaskStorage();
        this.lst = TaskList.makeTaskList();
        this.parser = new Parser();
    }


    /**
     * Starts the execution of the Snom.
     */
    public void runBot() {
        this.ui.greet();
        boolean isActive = true;
        while (isActive) {
            try {
                String cmd = this.ui.getCommand();
                Command command = Command.makeCommand(cmd);
                isActive = this.parser.processCommand(command, this.lst, this.data);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Snom s = new Snom();
        s.runBot();
    }
}
