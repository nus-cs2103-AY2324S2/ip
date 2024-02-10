package ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class abstracts all the UI of the GUI for both input and output for the bot.
 * It handles the stage creation process, and leaves the finer details to other classes,
 * such as ChatManager managing the textual inputs and outputs.
**/
public class UiManager {
    private Stage stage;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a UiManager object.
     *
     * @param stage The stage of the GUI.
     */
    public UiManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * This initializes the object, helping to create the UI
     * with preset values.
     */
    public void setupUI() {
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Returns the Dialog Label.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Returns the Dialog Container for the GUI.
     */
    public VBox getDialogContainer() {
        return this.dialogContainer;
    }

    /**
     * Returns the TextField element.
     */
    public TextField getUserInput() {
        return this.userInput;
    }

    /**
     * Returns the Send Button element.
     */
    public Button getSendButton() {
        return this.sendButton;
    }

    /**
     * Returns the User image.
     */
    public Image getUserImage() {
        return this.userImage;
    }

    /**
     * Returns the Duke (bot) image.
     */
    public Image getDukeImage() {
        return this.dukeImage;
    }
}
