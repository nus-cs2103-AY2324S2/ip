import javafx.fxml.FXML;
import riz.data.*;
import riz.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This is the main class of the RizBot.
 * This class mainly consists of the interactions between the classes TaskList, Storage.
 * The filepath in which data is saved is also determined here as it is passed into the Storage class.
 * The input given by the user, the user's TaskList and Saved data are passed into a Parser which processes the input.
 */
public class Riz extends Application{
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Theo.png"));
    private Image riz = new Image(this.getClass().getResourceAsStream("/images/Riz.png"));
    private TaskList taskList;
    private Storage storage;

    public Riz() {
        this.storage = new Storage("riz/data/riz.txt");
        this.taskList = new TaskList(this.storage.loadFromFile());
    }

    /**
     * The constructor initialises a Storage object with the filepath given
     * and loads the TaskList with the tasks from memory using the Storage object.
     * @param filePath The filepath in which we want our data to be stored.
     */
    public Riz(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadFromFile());
    }

    @Override
    public void start(Stage stage) {
        Label welcomeMessageLabel = new Label("Welcome to the best to-do list manager... RizBot...");

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
        stage.setTitle("RizBot");
        stage.setResizable(false);
        stage.setMinWidth(400.0);
        stage.setMinHeight(600.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().add(DialogBox.getRizDialog(welcomeMessageLabel, new ImageView(riz)));
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput () {
        Label userText = new Label(userInput.getText() + " ");
        Label RizText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getRizDialog(RizText, new ImageView(riz))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse (String input){
        return " " + Parser.parse(this.taskList, this.storage, input);
    }

    /*
    /**
     * The run method initialises a scanner and make sures the correct input is
     * being fed to the Parser class. It also terminates the program when
     * the 'bye' command is given.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String dotted = "-----------------------------------";
        //greetings
        Ui.printGreetings();
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                isRunning = false;
                Ui.printGoodbye();
            } else {
                Parser.parse(this.taskList, this.storage, input);
            }
        }
    }
/*
    /**
     * Main method.
     * Initializes the RizBot with a filepath.
     * Then calls the run() method.
     * @param args Command-line arguments provided to the program.
     * These arguments can be used to customize the behavior of the program.
     */

}