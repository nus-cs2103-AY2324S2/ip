package lex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lex.parser.Parser;
import lex.parser.command.Command;
import lex.storage.Storage;
import lex.tasks.TaskList;
import lex.ui.MainWindow;
import lex.ui.Ui;

/**
 * Represents the main class of the Lex chatbot.
 */
public class Lex extends Application {
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructor for the Lex class.
     *
     * @param filePath The file path of the data file.
     */
    public Lex(String filePath) {
        ui = new Ui(new StringBuffer());
        Storage storage = new Storage(filePath);

        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }

        parser = new Parser(tasks, ui, storage);
    }

    /**
     * Constructor for the Lex class.
     */
    public Lex() {
        this("./data.json");
    }

    /**
     * Runs the Lex chatbot.
     *
     * @param input The input to be processed.
     */
    public String run(String input) {
        assert input != null : "Input should not be null";

        try {
            Command command = parser.parse(input);

            if (command.execute()) {
                System.exit(0);
            }

            return ui.flush();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setLex(this);
        stage.show();
    }
}
