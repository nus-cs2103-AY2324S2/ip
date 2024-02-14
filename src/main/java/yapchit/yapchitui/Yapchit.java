package yapchit.yapchitui;

import yapchit.yapchitbackend.YapchitBackend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Yapchit is a bot that allows users to create and manage their tasks. The Yapchit class
 * is the entry point into the program and encapsulates a number of other classes that
 * enable the functionality of Yapchit.
 *
 * @author Archit Goswami
 * @version 1.0
 * @since 2024-02-01
 */
public class Yapchit extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image yapchit = new Image(this.getClass().getResourceAsStream("/images/DaYapchit.png"));

    private YapchitBackend yapchitBackend;

    private boolean hasNext = true;


    public Yapchit(){
        this.yapchitBackend= new YapchitBackend("./src/main/data/dataStore.txt");
    }

    @Override
    public void start(Stage stage) {
        initialSetup(stage);

        DialogBox yapchitInro = DialogBox.getYapchitDialog(getDialogLabel(yapchitBackend.getIntro()),
                new ImageView(yapchit));
        dialogContainer.getChildren().add(yapchitInro);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
            if(!hasNext){
                stage.close();
            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Responds to user input based on input command. Enables core functionality of yapchit.
     */
    private void handleUserInput() {
        String inputText = userInput.getText();
        Label yapchitText;
        Label userText = new Label(inputText);

        if (yapchitBackend.checkIsBye(inputText)){
            yapchitText = new Label(yapchitBackend.getOutro());
            this.hasNext = false;
        } else {
            yapchitText = new Label(yapchitBackend.run(inputText));
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getYapchitDialog(yapchitText, new ImageView(yapchit))
        );

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "bob";
    }

    private void initialSetup(Stage stage){
        setupDialogContainer();
        setUpScrollPane();
        AnchorPane mainLayout = createNewAnchorPane();
        setupStage(stage, mainLayout);
        setUpAnchorPane(mainLayout);
    }

    private void setupDialogContainer() {
        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    private void setUpScrollPane() {
        scrollPane = new ScrollPane();
        scrollPane.setContent(dialogContainer);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private AnchorPane createNewAnchorPane() {
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        return mainLayout;
    }

    private void setupStage(Stage stage, AnchorPane mainLayout) {
        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Yapchit");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void setUpAnchorPane(AnchorPane mainLayout) {
        mainLayout.setPrefSize(400.0, 600.0);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}
