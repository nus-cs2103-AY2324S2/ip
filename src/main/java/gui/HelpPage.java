package gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the HelpPage popup window. Provides the layout of the window.
 */
public class HelpPage {
    @FXML
    private static Stage popUpWindow;
    @FXML
    private static Scene scene1;
    @FXML
    private static VBox layout;
    @FXML
    private static Label label1;
    @FXML
    private static Button button1;

    /**
     * Displays the help list as a popup window.
     */
    public static void display() {
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Help List");

        label1 = new Label("Oink! Here are the Command Words:\n\n"
                + "<list>\n  - displays the list of task\n"
                + "<todo ...>\n  - to add new task\n"
                + "<deadline ... /by dd/MM/yyyy>\n  - to add task with deadline\n"
                + "<event ... /from ... /to ...>\n  - to add an event\n"
                + "<mark [task no.]>\n  - to mark a task done\n"
                + "<unmark [task no.]>\n  - to unmark a task\n"
                + "<find ...>\n  - to find tasks with matching descriptions\n"
                + "<delete [task no.]>\n  - to delete a task\n");
        label1.setFont(new Font("Monospaced Regular", 12));
        label1.setPadding(new Insets(0, 0, 50, 0));

        button1 = new Button("Oink! I'm done!");
        button1.setOnAction(e -> popUpWindow.close());

        layout = new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);

        scene1 = new Scene(layout, 350, 400);
        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();
    }
}
