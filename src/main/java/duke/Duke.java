package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import duke.exceptions.UnrecognizedException;
import duke.exceptions.MissingInputException;

/**
 * Class representing Duke
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Ui chatty = new Ui();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        String message;

        while (chatty.hasFinished() == false) {
            try {
                message = chatty.interact(input);
            } catch (UnrecognizedException e) {
                message = "Apologies Sir! I am not equipped to handle such a command!";
            } catch (MissingInputException e) {
                message = "Apologies Sir! You did not include the name of the item!";
            }
            System.out.println(message);
            input = reader.nextLine();
        }

        reader.close();

    }

    /**
     * Returns appropriate response based on input.
     * @param input User input.
     * @return Response to user input.
     * @throws FileNotFoundException If text file where task list information is saved cannot be found.
     * @throws IOException If text file cannot be created.
     */
    public String getResponse(String input) throws FileNotFoundException, IOException {
        Ui chatty = new Ui();
        String message;
        try {
            message = chatty.interact(input);
        } catch (UnrecognizedException e) {
            message = "Apologies Sir! I am not equipped to handle such a command!";
        } catch (MissingInputException e) {
            message = "Apologies Sir! You did not include the name of the item!";
        }

        return message;
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {
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
            try {
                handleUserInput();
            } catch (FileNotFoundException e) {
                System.out.println("TaskList file cannot be found");
            } catch (IOException e) {
                System.out.println("File Creation failed");
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (FileNotFoundException e) {
                System.out.println("TaskList file cannot be found");
            } catch (IOException e) {
                System.out.println("File Creation failed");
            }
        });
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() throws FileNotFoundException, IOException {
        // Label userText = new Label(userInput.getText());
        // Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    // public String getResponse(String input) {
    //     return "Duke heard: " + input;
    // }
}
