package duke;

import exceptions.ArgumentException;
import exceptions.StorageException;
import javafx.application.Application;
import javafx.stage.Stage;
import storage.Storage;
import storage.TaskList;
import ui.UserInterface;

/**
 * Represents the whole application, contains methods for initialisation and termination of the program.
 */
public class Stille extends Application {
    private final UserInterface ui;
    private final Storage storage;
    private final TaskList list;

    /**
     * Instantiates application.
     */
    public Stille() {
        this.storage = new Storage();
        this.list = new TaskList();
        this.ui = new UserInterface(this.list, this.storage);
    }

    /**
     * {@inheritDoc}
     * Additionally handles initialisation of TaskList from local storage.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. Applications may create other stages, if needed,
     *                     but they will not be primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(this.ui.getScene());
        primaryStage.setTitle("Stille");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        primaryStage.show();

        try {
            list.loadFromSaveFormat(storage.load());
        } catch (StorageException e) {
            ui.showError(e);
        } catch (ArgumentException e) {
            ui.showError(e);
        }

        ui.showOpeningMessage();
    }

    /**
     * {@inheritDoc}
     * Additionally handles saving TaskList to local storage.
     */
    @Override
    public void stop() {
        try {
            this.storage.save(this.list.toSaveFormat());
        } catch (StorageException e) {
            ui.showError(e);
        } finally {
            ui.showClosingMessage();
        }
    }
}
