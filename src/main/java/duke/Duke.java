package duke;

// import javafx.scene.control.Label;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.layout.Region;
// import javafx.scene.image.ImageView;

// import javafx.stage.Stage;

// import javafx.application.Application;

import duke.tasks.TaskList;

/**
 * Main class that runs the program
 */
public class Duke {

    private final String LOGO = "" 
            + "\t    __    _                 \n"
            + "\t   / /   (_)___  __  _______\n"
            + "\t  / /   / / __ \\/ / / / ___/\n"
            + "\t / /___/ / / / / /_/ (__  ) \n"
            + "\t/_____/_/_/ /_/\\__,_/____/  \n";

    private final String NAME = "Linus";

    private final String FILE_PATH = "./data/linus.txt";

    private Ui ui;

    private TaskList taskList;

    private Parser parser;

    private Storage storage;

    // private ScrollPane scrollPane;
    // private VBox dialogContainer;
    // private TextField userInput;
    // private Button sendButton;
    // private Scene scene;
    // private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    // private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Chatbot
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.parser = new Parser(taskList, ui);
    }

    // @Override
    // public void start(Stage stage) {

    //     //Step 1. Setting up required components

    //     //The container for the content of the chat to scroll.
    //     scrollPane = new ScrollPane();
    //     dialogContainer = new VBox();
    //     scrollPane.setContent(dialogContainer);

    //     userInput = new TextField();
    //     sendButton = new Button("Send");

    //     AnchorPane mainLayout = new AnchorPane();
    //     mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

    //     scene = new Scene(mainLayout);

    //     stage.setScene(scene);
    //     stage.show();

    //     //Step 2. Formatting the window to look as expected
    //     stage.setTitle("Duke");
    //     stage.setResizable(false);
    //     stage.setMinHeight(600.0);
    //     stage.setMinWidth(400.0);

    //     mainLayout.setPrefSize(400.0, 600.0);

    //     scrollPane.setPrefSize(385, 535);
    //     scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    //     scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    //     scrollPane.setVvalue(1.0);
    //     scrollPane.setFitToWidth(true);

    //     //You will need to import `javafx.scene.layout.Region` for this.
    //     dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    //     userInput.setPrefWidth(325.0);

    //     sendButton.setPrefWidth(55.0);

    //     AnchorPane.setTopAnchor(scrollPane, 1.0);

    //     AnchorPane.setBottomAnchor(sendButton, 1.0);
    //     AnchorPane.setRightAnchor(sendButton, 1.0);

    //     AnchorPane.setLeftAnchor(userInput , 1.0);
    //     AnchorPane.setBottomAnchor(userInput, 1.0);

    //     sendButton.setOnMouseClicked((event) -> {
    //         dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
    //         userInput.clear();
    //     });

    //     userInput.setOnAction((event) -> {
    //         dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
    //         userInput.clear();
    //     });

    //     dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    //     //Part 3. Add functionality to handle user input.
    //     sendButton.setOnMouseClicked((event) -> {
    //         handleUserInput();
    //     });

    //     userInput.setOnAction((event) -> {
    //         handleUserInput();
    //     });
        
    // }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    // private Label getDialogLabel(String text) {
    //     // You will need to import `javafx.scene.control.Label`.
    //     Label textToAdd = new Label(text);
    //     textToAdd.setWrapText(true);

    //     return textToAdd;
    // }

    // /**
    //  * Iteration 2:
    //  * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
    //  * the dialog container. Clears the user input after processing.
    //  */
    // private void handleUserInput() {
    //     Label userText = new Label(userInput.getText());
    //     Label dukeText = new Label(getResponse(userInput.getText()));
    //     dialogContainer.getChildren().addAll(
    //             DialogBox.getUserDialog(userText, new ImageView(user)),
    //             DialogBox.getDukeDialog(dukeText, new ImageView(duke))
    //     );
    //     userInput.clear();
    // }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Starts the program
     */
    public void startProgram() {
        ui.print("Hello from\n" + LOGO);
        ui.greeting(NAME);
        this.loadData();
        parser.run();
        this.saveData();
    }

    /**
     * Loads the TaskList tasks data from file
     */
    public void loadData() {
        storage.loadData(taskList, ui);
    }

    /**
     * Saves TaskList tasks into file
     */
    public void saveData() {
        storage.saveData(taskList, ui);
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.startProgram();

    }
}

