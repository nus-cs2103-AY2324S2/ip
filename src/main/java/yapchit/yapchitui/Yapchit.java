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
        assert yapchitBackend != null;
    }

    @Override
    public void start(Stage stage) {

        initialSetup(stage);


        DialogBox yapchitInro = DialogBox.getYapchitDialog(getDialogLabel(yapchitBackend.getIntro()), new ImageView(yapchit));
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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
    private void initialSetup(Stage stage){
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

        stage.setTitle("Yapchit");
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
    }




}
