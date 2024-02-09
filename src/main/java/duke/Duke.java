package duke;

import duke.commands.Command;

/**
 * Represents a ChatBot class.
 */
public class Duke {
    TaskList taskList;
    private String filePath = "duke/taskList.txt";
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for the ChatBot class.
     *
     * @param filePath The filepath to the file that contains recorded tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = this.storage.load();
        this.ui = new Ui();
    }

    /**
     * Run the ChatBot to interact with user.
     */
    public void run() {
        this.ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand, this.ui,this.taskList, this.storage);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./taskList.txt").run();
    }
}
