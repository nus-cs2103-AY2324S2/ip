package campus;

import campus.infrastructure.Parser;
import campus.infrastructure.Storage;
import campus.infrastructure.TaskList;
import campus.infrastructure.Ui;

/**
 * Contains the logic for the ChatBot named 'Campus'
 */
public class Campus {
    private final Ui ui;
    private final Parser parser;

    /**
     * Creation of a Campus instance
     */
    public Campus() {
        String filePath = "src/main/java/campus/dataTest.txt";
        this.ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(filePath);
        this.parser = new Parser(this.ui, taskList, storage);
    }

    public static void main(String[] args) {
        new Campus();
    }

    public String respond(String input) {
        return this.parser.respond(input);
    }

    public String greet() {
        return this.ui.greet();
    }

    public String exit() {
        return this.ui.exit();
    }
}
