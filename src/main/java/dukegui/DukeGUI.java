package dukegui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region; // customize the layout
import javafx.scene.control.Label; // responsive to controls (keypress/mouse click)
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DukeGUI extends Application {
  @FXML
  private ScrollPane scrollPane;
  @FXML
  private VBox dialogContainer;
  @FXML
  private TextField userInput;
  @FXML
  private Button sendButton;
  private Scene scene;
  private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
  private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

  public static void main(String[] args) {
    // ...
  }


  @Override
  public void start(Stage stage) {
    /*
    //Step 1. Setting up required components

    //The container for the content of the chat to scroll.
    scrollPane = new ScrollPane();
    dialogContainer = new VBox();
    scrollPane.setContent(dialogContainer);

    userInput = new TextField();
    sendButton = new Button("Send");

    AnchorPane mainLayout = new AnchorPane();
    mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

    scene = new Scene(mainLayout);

    stage.setScene(scene);
    stage.show();

    //Step 2. Formatting the window to look as expected
    stage.setTitle("DukeGUI");
    stage.setResizable(false);
    stage.setMinHeight(600.0);
    stage.setMinWidth(400.0);

    mainLayout.setPrefSize(400.0, 600.0);

    scrollPane.setPrefSize(385, 535);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    scrollPane.setVvalue(1.0);
    scrollPane.setFitToWidth(true);

    //You will need to import `javafx.scene.layout.Region` for this.
    dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    //Scroll down to the end every time dialogContainer's height changes.
    dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    userInput.setPrefWidth(325.0);

    sendButton.setPrefWidth(55.0);

    AnchorPane.setTopAnchor(scrollPane, 1.0);

    AnchorPane.setBottomAnchor(sendButton, 1.0);
    AnchorPane.setRightAnchor(sendButton, 1.0);

    AnchorPane.setLeftAnchor(userInput , 1.0);
    AnchorPane.setBottomAnchor(userInput, 1.0);

    //Part 3. Add functionality to handle user input.
    sendButton.setOnMouseClicked((event) -> {
      handleUserInput();
    });

    userInput.setOnAction((event) -> {
      handleUserInput();
    });
    */
  }

  /**
   * Iteration 1:
   * Creates a label with the specified text and adds it to the dialog container.
   * @param text String containing text to add
   * @return a label with the specified text that has word wrap enabled.
   */
  /*
  private Label getDialogLabel(String text) {
    // You will need to import `javafx.scene.control.Label`.
    Label textToAdd = new Label(text);
    textToAdd.setWrapText(true);

    return textToAdd;
  }
  */

  /**
   * Iteration 2:
   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
   * the dialog container. Clears the user input after processing.
   */
  /*
  @FXML
  private void handleUserInput() {
    String userText = String.valueOf(new Label(userInput.getText()));
    String dukeText = String.valueOf(new Label(getResponse(userInput.getText())));
    dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user).getImage()),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke).getImage())
    );
    userInput.clear();
  }
  */

  /**
   * You should have your own function to generate a response to user input.
   * Replace this stub with your completed method.
   */
  String getResponse(String input) {
    return "Duke heard: " + input;
  }
}