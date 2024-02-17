package sylvia;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sylvia.ui.MainWindow;
import sylvia.ui.UI;

/**
 * The main class to start the bot.
 */
public class Main extends Application {
    private UI ui;
    private Sylvia sylvia;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            // set up the stage
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();

            // set up the UI controller and start the bot
            MainWindow mw = fxmlLoader.<MainWindow>getController();
            ui = new UI(mw);
            sylvia = new Sylvia(ui);
            mw.setSylvia(sylvia);

            ui.showWelcomeMessage();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @Override
    public void stop() {
        ui.showExitMessage();
        sylvia.exit();
    }
}
