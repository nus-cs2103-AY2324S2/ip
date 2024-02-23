package tasklist.gui;
import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
<<<<<<< HEAD:src/main/java/tasklist/gui/DialogBox.java
import javafx.scene.Scene;
import javafx.scene.control.Button;
=======
>>>>>>> branch-A-CodeQuality:src/main/java/tasklist/DialogBox.java
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tasklist.TaskList;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML 
    private Button actionButton; 

<<<<<<< HEAD:src/main/java/tasklist/gui/DialogBox.java
    private DialogBox(String text, Image img, boolean isUser, TaskList taskList) {
=======
    private DialogBox(String text, Image img, boolean isUser) {
>>>>>>> branch-A-CodeQuality:src/main/java/tasklist/DialogBox.java
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            Parent root = fxmlLoader.load();
            if (isUser) {
                String existingStyle = root.getStyle();
                existingStyle += "-fx-background-color: #C4A484;";
                root.setStyle(existingStyle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        
         if (taskList!=null) {
            actionButton.setVisible(true);
            actionButton.setOnAction(event -> {
                try {
                    FXMLLoader fxmlLoader_sched = new FXMLLoader(MainWindow.class.getResource("/view/ScheduleWindow.fxml"));
                    Parent root = fxmlLoader_sched.load();
        
                    Stage popupStage = new Stage();
                    Scene scene = new Scene(root);
                    popupStage.setScene(scene);
        
                    ScheduleWindow controller = fxmlLoader_sched.getController();
                    controller.setStage(popupStage, taskList); 
        
                    popupStage.show(); 
                } catch (IOException e) {
                    e.printStackTrace(); 
                }
            });
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
<<<<<<< HEAD:src/main/java/tasklist/gui/DialogBox.java
        return new DialogBox(text, img, true, null);
    }

    public static DialogBox getDukeDialog(String text, Image img, TaskList tasklist) {
        var db = new DialogBox(text, img, false, tasklist);
=======
        return new DialogBox(text, img, true);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
>>>>>>> branch-A-CodeQuality:src/main/java/tasklist/DialogBox.java
        db.flip();
        return db;
    }
}
