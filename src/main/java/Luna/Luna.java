package Luna;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Luna {
    private final Storage storage;
    private final TaskList tasks;

    private final Ui ui;

    public Luna() {
        storage = new Storage("taskList");
        tasks = new TaskList();
        ui = new Ui("Luna");
    }

    public void run() {

        assert ui != null : "ui should not be equals to null";
        String userInput = ui.readInput();
        Command c = Parser.parse(userInput);

        assert storage != null : "storage should not be equals to null";
        assert tasks != null : "tasks should not be equals to null";
        c.execute(tasks,ui,storage);
        run();
    }

    public void begin() {

        ui.greet();
        run();
    }

    public void readIn(String input) {

        String userInput = ui.readInput(input);
        assert userInput != null : "user input should not be null";

        Command c = Parser.parse(userInput);
        assert c != null : "command should not be null";

        c.execute(tasks,ui,storage);
    }

    public static void main(String[] args)  {
        new Luna().begin();
    }



}

