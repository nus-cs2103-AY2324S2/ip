package duke;
import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private String storePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.storePath = filePath;
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = storage.load();
        } catch (DukeException e) {
            ui.printError(e);
            this.taskList = new TaskList();
        }
    }
    public void greet() {
        String name = "Yippee";
        System.out.println("    ____________________________________________________________");
        System.out.printf("      Hello! I'm %s\n", name);
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }
    public void run() {
        greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandString = this.ui.readCommand();
                ui.showLine();
                Command command = new Parser().parseCommand(commandString);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                if (!isExit) {
                    ui.showLine();
                }
            }
        }
        this.ui.endCommands();
    }
    public static void main(String[] args) {
        new Duke("duke/data/storage.txt").run();
    }
}
