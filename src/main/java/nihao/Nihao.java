package nihao;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import nihao.action.Action;
import nihao.action.ExitAction;
import nihao.handler.InputHandler;
import nihao.handler.PrintHandler;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nihao.ui.MainWindowController;

/**
 * Contains the main application logic for the Nihao app.
 */
public class Nihao extends Application{
    public static final Nihao instance = new Nihao();
    public Nihao() {}

    /**
     * Reads user input and executes the main logic.
     */
    public void run() {
        PrintHandler.printInit();

//        File myInput = new File("text-ui-test/input.txt");
//        try {
//            Scanner scanner = new Scanner(myInput);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                try {
                    Action action = InputHandler.handleInput(input);
                    action.execute();
                    if (action instanceof ExitAction) {
                        break;
                    }
                } catch (Exception e) {
                    PrintHandler.printException(e);
                }
            }
            scanner.close();
//        } catch (FileNotFoundException e) {
//            PrintHandler.printWithDivider("File not found");
//        }
    }

    public void start(Stage stage) {
        try {
            stage.setTitle("Nihao");
            stage.setResizable(false);
            stage.setMinHeight(600);
            stage.setMinWidth(400);
            stage.getIcons().add(new javafx.scene.image.Image(this.getClass().getResourceAsStream("/images/logo_600.png")));

            FXMLLoader fxmlLoader = new FXMLLoader(Nihao.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            String css = this.getClass().getResource("/styles/cupertino-dark.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            fxmlLoader.<MainWindowController>getController().setNihao(Nihao.instance);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String text) {
        try {
            Action action = InputHandler.handleInput(text);
            return action.execute();
//            if (action instanceof ExitAction) {
//            }
        } catch (Exception e) {
            return PrintHandler.printException(e);
        }
    }

}
