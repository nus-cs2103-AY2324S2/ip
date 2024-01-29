/**
 * Contains the logic for the ChatBot named 'Campus'
 */
public class Campus {
    private final Ui ui;
    private final Parser parser;

    public Campus(String filePath) {
        this.ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(filePath);
        this.parser = new Parser(this.ui, taskList, storage);
    }

    public void run() {
        this.ui.greet();
        this.parser.listen();
        this.ui.exit();
    }

    /**
     * Main Driver Logic of the Campus Class which handles user inputs and sorts them into cases
     */
    public static void main(String[] args) {
        new Campus("src/main/java/data.txt").run();
    }
}
