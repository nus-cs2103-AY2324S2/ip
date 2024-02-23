package duke.main;
import java.io.IOException;

import duke.exception.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *  Main executable for programme
 */
public class TalkingBox extends Application {
    static final String PATH = "save.txt";
    private Storage storage;
    private TaskList taskList;
    private NotesList notesList;
    Ui ui;
    Parser parser;

    public TalkingBox() {
        taskList = new TaskList();
        notesList = new NotesList();
        ui = new Ui(this.taskList, this.notesList);
        parser = new Parser(this.taskList, this.ui, this.notesList);
        try {
            storage = new Storage();
        } catch (FileNotFoundException f) {
            Ui.printException(f);
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TalkingBox.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setProgramme(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that executes the main programme
     *
     * @throws IOException
     */
    public void run() {
        //boolean isExit = false;
        boolean isError = false;
        while (!isError) {
            String command = this.ui.readLine();
            //isExit = this.parser.isExit(command);
            this.parser.parse(command);
            Storage.store();
        }
        //this.ui.printExitMessage();
    }

    public static void main(String[] args) throws IOException {
        new TalkingBox().run();
    }
}