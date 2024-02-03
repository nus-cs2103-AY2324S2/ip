package podz;

import podz.command.ByeCommand;
import podz.command.Command;
import podz.parser.Parser;
import podz.storage.Storage;
import podz.task.TaskList;
import podz.ui.Ui;

public class Podz {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private Podz() {
        storage = new Storage("./data/podz.txt");
        tasks = new TaskList(this.storage.loadTasks());
        ui = new Ui();
        parser = new Parser();
    }

    public static void main(String[] args) {
        Podz podz = new Podz();
        podz.run();
    }

    public void run() {
        ui.printGreeting();

        while (true) {
            boolean isExit = false;
            try {
                String input = this.ui.getInput();
                Command command = this.parser.parseCommand(input);
                command.setTasks(this.tasks);
                command.execute(this.ui);
                if (command instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (Exception e) {
                this.ui.printError(e);
            }
            if (isExit) {
                break;
            }
        }        
    }
}
