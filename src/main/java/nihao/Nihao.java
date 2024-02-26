package nihao;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nihao.action.Action;
import nihao.action.ExitAction;
import nihao.handler.InputHandler;
import nihao.handler.PrintHandler;
import nihao.ui.MainWindowController;

/**
 * Contains the main application logic for the Nihao app.
 */
public class Nihao extends Application {
    public static final Nihao INSTANCE = new Nihao();
    public Nihao() {}

    /**
     * Starts the application.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     */
    public void start(Stage stage) {
        try {
            stage.setTitle("Nihao");
            stage.setResizable(false);
            stage.setMinHeight(600);
            stage.setMinWidth(400);
            stage.getIcons().add(new javafx.scene.image.Image(
                    this.getClass().getResourceAsStream("/images/logo_600.png")
            ));

            FXMLLoader fxmlLoader = new FXMLLoader(Nihao.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            String css = this.getClass().getResource("/styles/cupertino-dark.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            fxmlLoader.<MainWindowController>getController().setNihao(Nihao.INSTANCE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the response from the app based on the user input.
     * @param text the input text from the user
     * @return the response from the app
     */
    public String getResponse(String text) {
        try {
            Action action = InputHandler.handleInput(text);
            if (action instanceof ExitAction) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                Platform.exit();
                                System.exit(0);
                            }
                        },
                        3000
                );

            }
            return action.execute();
        } catch (Exception e) {
            return PrintHandler.printException(e);
        }
    }

}
