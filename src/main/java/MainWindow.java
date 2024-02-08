import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import ui.DialogBox;

public class MainWindow extends Application {
  private ScrollPane scrollPane;
  private VBox dialogContainer;
  private TextField userInput;
  private Button sendButton;
  private Scene scene;
  private GeePeeTee geePeeTee;
  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
  private Image geepeeteeImage = new Image(this.getClass().getResourceAsStream("/images/geepeetee.jpeg"));

   @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setGeePeeTee(GeePeeTee geePeeTee) {
        this.geePeeTee = geePeeTee;
    }

  @Override
  public void start(Stage stage) {
    // Step 1. Setting up required components
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

    // Step 2. Formatting the window to look as expected
    stage.setTitle("Duke");
    stage.setResizable(false);
    stage.setMinHeight(600.0);
    stage.setMinWidth(400.0);

    mainLayout.setPrefSize(400.0, 600.0);

    scrollPane.setPrefSize(385, 535);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    scrollPane.setVvalue(1.0);
    scrollPane.setFitToWidth(true);

    dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    userInput.setPrefWidth(325.0);

    sendButton.setPrefWidth(55.0);

    AnchorPane.setTopAnchor(scrollPane, 1.0);

    AnchorPane.setBottomAnchor(sendButton, 1.0);
    AnchorPane.setRightAnchor(sendButton, 1.0);

    AnchorPane.setLeftAnchor(userInput, 1.0);
    AnchorPane.setBottomAnchor(userInput, 1.0);

    // Step 3. Add functionality to handle user input.
    sendButton.setOnMouseClicked((event) -> {
      handleUserInput();
    });

    userInput.setOnAction((event) -> {
      handleUserInput();
    });
    dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
  }


  /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = geePeeTee.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}
