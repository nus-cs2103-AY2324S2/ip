package duke;

import duke.task.TaskList;

/**
 * Represents a chat bot that helps user keep track of tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs an instance of the Duke Chatbot.
     *
     * @param filePath The filepath where task list data is stored.
     */
    public Duke(String filePath) {
        try {
            assert filePath.length() != 0 : "File Path must be defined";

            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.loadTaskList());
            this.parser = new Parser();
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Constructs an instance of the Duke Chatbot with no parameters.
     */
    public Duke() {
        String filePath = "src/main/data/caching.txt";
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.loadTaskList());
            this.parser = new Parser();
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Runs the program until the "bye" instruction is inputted by the user and interprets the command word.
     */
    public void run() {
        this.ui = new Ui(System.in);
        System.out.println(this.ui.greet());

        String instr = this.ui.getInstr();
        while (!instr.equals("bye")) {
            this.parser.parseInstr(this.tasks, this.storage, instr);
            instr = this.ui.getInstr();
        }
        System.out.println(this.ui.exit());
        this.ui.close();
    }

    /**
     * Calls the constructor and is the entry point of the program.
     *
     * @param args Input by the user.
     */
    public static void main(String[] args) {
        new Duke("src/main/data/caching.txt").run();
    }

    /**
     * Calls the parser class to retrieve response based on command.
     *
     * @param input Instructions by the user.
     * @return A string representing the response.
     */
    public String getResponse(String input) {
        assert this.parser != null : "Parser class must be instantiated first.";

        return this.parser.parseInstr(this.tasks, this.storage, input);
    }
}
