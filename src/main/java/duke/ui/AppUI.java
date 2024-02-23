package duke.ui;

import duke.commons.exceptions.DukeException;
import duke.controller.LogicController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppUI {

    private Stage stage;
    private Scene scene;
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image derek = new Image(this.getClass().getResourceAsStream("/images/ChineseBeaver.png"));

    private LogicController logicController; // Dependency

    public AppUI(Stage stage, LogicController logicController) {
        this.stage = stage;
        this.logicController = logicController;
        initComponents();
        initLayout();
        initEventHandlers();
    }

    private void initComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInputField = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInputField, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void initLayout() {
        stage.setTitle("Derek");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 800.0);

        scrollPane.setPrefSize(585, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInputField.setPrefWidth(525.0);

        sendButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInputField, 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);
    }

    private void initEventHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInputField.setOnAction((event) -> {
            handleUserInput();
        });

        // Scrolls to the bottom whenever height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    public void createUserDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.createUserDialog(new Label(text), new ImageView(user)));
    }

    public void createAgentDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.createAgentDialog(new Label(text), new ImageView(derek)));
    }

    private void handleUserInput() {
        try {
            String userInput = userInputField.getText();
            createUserDialog(userInput);

            String response = logicController.processUserInput(userInput);
            createAgentDialog(response);

        } catch (DukeException e) {
            createAgentDialog(e.getMessage());
        } finally {
            userInputField.clear();
        }
    }

}
