package duke;

import java.io.IOException;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Duke extends Application {
    Parser parser = new Parser();
    TaskList tasks = new TaskList();
    Storage storage = new Storage();

    @Override
    public void start(Stage stage) {
        Commands.registerCommands();
        try {
            tasks.loadFromSavedData(storage.loadFromFile(), parser);
        } catch (DukeException e) {
            storage.deleteFile();
            //ui.printCorruptedFileMessage(e);
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {
        Command command;
        try {
            command = parser.parse(input);
            String result = command.execute(tasks);
            storage.saveToFile(tasks.getSaveData());
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
