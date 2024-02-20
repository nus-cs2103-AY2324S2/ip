package duke;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * duke.Main class of the program.
 *
 * @author Tania Tan Shu Qi
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userPic = new Image(this.getClass().getResourceAsStream("/images/DaCHOCO.jpg"));
    private Image eueu = new Image(this.getClass().getResourceAsStream("/images/DaEUEU.jpg"));
    public Duke() {

    }

    File f = new File("data/EUEU.txt");
    File contFile = new File("data/Contacts.txt");
    Scanner user = new Scanner(System.in);

    Storage storage = new Storage(f);
    TaskList tasklist = new TaskList(storage);
    Storage contStorage = new Storage(contFile);
    ContactsList contactsList = new ContactsList (contStorage);

    public static void main(String[] args) throws IOException {

        File f = new File("data/EUEU.txt");
        Scanner user = new Scanner(System.in);

    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

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

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);



//        Label welcomeMessage = new Label ("Hi babyyy! It's your EUEU!! \n"
//                                                + "What are you doing today??");

        Label welcomeMessage = new Label (tasklist.list());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, new ImageView(eueu)));


        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));




    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userPic)),
                DialogBox.getDukeDialog(dukeText, new ImageView(eueu))
        );

        userInput.clear();
    }

    /**
     * Gets Chatbot response based on user input.
     * 
     * @param input String user input to be parsed.
     * @return String response of Chatbot.
     * @throws IOException When string input do not match any of the CLI of the Parser class.
     */
    String getResponse(String input) throws IOException {
        ContactsParser conts = new ContactsParser(contactsList);

        if (input.startsWith("cont")) {
            try {
                String str = input.substring(5);
                return conts.parsing(str);
            } catch (StringIndexOutOfBoundsException e){
                return "ENTER (CONTACT) INSTRUCTION";
            }
        } else {
            Parser parse = new Parser(tasklist, contactsList);
            return parse.parsing(input);
        }

    }
}