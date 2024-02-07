package yippee;
import yippee.commands.Command;
import yippee.exceptions.YippeeException;
/**
 * Implements the Yippee chatbot that records and store tasks from the user.
 * It is able to add, delete, and mark tasks as complete/incomplete
 */
public class Yippee {
    private String storePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Instantiates the Yippee bot instance.
     * @param filePath Path where data is stored.
     */
    public Yippee(String filePath) {
        this.storePath = filePath;
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = storage.load();
        } catch (YippeeException e) {
            ui.printError(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String name = "Yippee";
        System.out.println("    ____________________________________________________________");
        System.out.printf("      Hello! I'm %s\n", name);
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Runs bot instance and processes any inputs from user.
     */
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
            } catch (YippeeException e) {
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
        new Yippee("yippee/data/storage.txt").run();
    }
}
