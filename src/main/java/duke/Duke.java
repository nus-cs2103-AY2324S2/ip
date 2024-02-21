package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The main class.
 */
public class Duke extends Application {

    TaskList taskList;
    private String output;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    public Duke() {
    }
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

        stage.setTitle("Greg");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        taskList = new TaskList(this);
        Storage.read(taskList, this);
        greet();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );

        if (userInput.getText().equals("bye")) {
            sayBye();
            Stage stage = (Stage) scene.getWindow();
            stage.close();
        } else {
            getResponse(userInput.getText());

        }
        userInput.clear();
    }

    /**
     * Outputs Greg's dialog box given the words to be output
     * @param input the words to output
     */
    public void output(String input) {
        Label userText = new Label(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(userText, new ImageView(duke))
        );

    }

    /**
     * Sends the user input to the parser
     *
     * @param input the user input
     */
    private void getResponse(String input) {
        Parser.commands(taskList, input, false, false, this);
    }

    /**
     * The method used to greet the user upon startup.
     */
    public void greet() {
        String greet = fillerLine() +
                "    Hello! I am Greg.\n    What can I do for you?\n" +
                fillerLine();
        output(greet);
    }

    /**
     * Runs the shutdown sequence.
     * Saves then says bye.
     */
    public void sayBye() {
        Storage.save(taskList);
        String bye = fillerLine() +
                "    Goodbye! Hope to see you again soon!\n" +
                fillerLine();
        System.out.println(bye);
    }

    /**
     * Prints a line of dashes
     */
    public String fillerLine() {
        return "    _______________________________________\n";
    }
}
