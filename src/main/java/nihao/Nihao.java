package nihao;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import nihao.action.Action;
import nihao.action.ExitAction;
import nihao.handler.InputHandler;
import nihao.handler.PrintHandler;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import nihao.ui.DialogueBox;

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

    private ScrollPane dialoguePane;
    private VBox dialogueContainer;
    private TextField inputField;
    private Button inputButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image nihaoImage = new Image(this.getClass().getResourceAsStream("/images/logo_600.png"));
    @Override
    public void start(Stage stage) {
        stage.setTitle("Nihao");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);


        dialoguePane = new ScrollPane();
        dialoguePane.setPrefSize(385, 535);
        dialoguePane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dialoguePane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        dialoguePane.setVvalue(1);
        dialoguePane.setFitToWidth(true);

        dialogueContainer = new VBox();
        dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogueContainer.heightProperty().addListener(observable -> dialoguePane.setVvalue(1.0));
        dialoguePane.setContent(dialogueContainer);


        inputField = new TextField();
        inputField.setPrefWidth(325);
        inputField.setOnAction(event -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                return;
            }
            handleUserInput();
            inputField.clear();
        });

        inputButton = new Button("Send");
        inputButton.setPrefWidth(55);
        inputButton.setOnMouseClicked(event -> {
            String input = inputField.getText();
            if (input.isEmpty()) {
                return;
            }
            handleUserInput();
            inputField.clear();
        });

        HBox inputBox = new HBox(inputField, inputButton);


        AnchorPane root = new AnchorPane(dialoguePane, inputBox);
        AnchorPane.setTopAnchor(dialoguePane, 1.0);
        AnchorPane.setBottomAnchor(inputBox, 1.0);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private String getResponse(String text) {
        try {
            Action action = InputHandler.handleInput(text);
            return action.execute();
//            if (action instanceof ExitAction) {
//            }
        } catch (Exception e) {
            return PrintHandler.printException(e);
        }
    }

    private void handleUserInput() {
        Label userText = new Label(inputField.getText());
        Label nihaoText = new Label(getResponse(inputField.getText()));
        dialogueContainer.getChildren().addAll(
                DialogueBox.getUserDialogue(userText, new ImageView(userImage)),
                DialogueBox.getNihaoDialogue(nihaoText, new ImageView(nihaoImage))
        );
    }
}
