package baron.views;

import java.net.URL;
import java.util.ResourceBundle;

import baron.Baron;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window FXML file
 */
public class MainWindow implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Baron baron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/da-user.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/da-duke.jpg"));

    @FXML
    private ListView<DialogBox> listView;
    private ObservableList<DialogBox> chats = FXCollections.observableArrayList();

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = baron.getResponse(input);
        chats.addAll(DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBaronDialog(response, botImage));
        listView.scrollTo(chats.size()-1);
        userInput.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(chats);
    }

    public void setBotInstance(Baron baron) {
        this.baron = baron;
    }
}
