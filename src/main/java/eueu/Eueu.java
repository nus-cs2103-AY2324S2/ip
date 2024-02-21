package eueu;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;

import java.util.Scanner;
/**
 * duke.Main class of the program.
 *
 * @author Tania Tan Shu Qi
 */
public class Eueu extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userPic = new Image(this.getClass().getResourceAsStream("/images/daCHOCO.jpg"));
    private Image eueu = new Image(this.getClass().getResourceAsStream("/images/daEUEU.jpg"));
    public Eueu() {}

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
    public void start(Stage stage) {

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

        stage.setTitle("Eueu");
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

        Label welcomeMessage = new Label (tasklist.list());
        dialogContainer.getChildren().add(DialogBox.getEueuDialog(welcomeMessage, new ImageView(eueu)));


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

    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label eueuText = new Label(getResponse(userInput.getText()));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userPic)),
                DialogBox.getEueuDialog(eueuText, new ImageView(eueu))
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
            Parser parse = new Parser(tasklist);
            return parse.parsing(input);
        }

    }
}