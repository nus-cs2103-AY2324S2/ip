package panda;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import panda.command.Command;
import panda.component.*;

import panda.exception.PandaException;

public class Panda extends Application {
    private TaskList tlist;
    private static final String FILEPATH = "list.txt";
    private Storage cacheFile;

    /**
     * Constructs a new Panda instance.
     * Initializes the user interface, storage file, and task list.
     */
    public Panda() {
        cacheFile = new Storage(FILEPATH);
        try {
            tlist = new TaskList(cacheFile.load());
        } catch (PandaException e) {
            tlist = new TaskList();
        }
    }

    /**
     * Generates response for the given user input
     * 
     * @param userInput the user input given
     * @return the response from the chat bot
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tlist, cacheFile);
        } catch (PandaException e) {
            return e.getMessage();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Panda.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPanda(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
