package duke;

import duke.command.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Duke is the main class for Task maintenance application Duke
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList taskList;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }

    /**
     * start method sets up the necessary UI elements.
     * Provides user interface elements for interactive task maintenance.
     * @param stage - javaFx Stage object as parameter
     *
     */
    @Override
    public void start(Stage stage) {
        //assuming filepath is specified as the only argument
        try {
            String filePath;
            if (this.getParameters().getRaw().size() > 0) {
                filePath = this.getParameters().getRaw().get(0);
            } else {
                filePath = "";
            }
            loadTaskListFromFile(filePath);
        }catch (DukeException de) {
            Alert alert = new Alert(Alert.AlertType.ERROR, de.getMessage());
        }

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Step 2
        stage.setTitle("Lighthouse");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0,600.0);
        scrollPane.setPrefSize(385,535);
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));


    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void handleUserInput() {
        String commandText = userInput.getText();

        Label userText = new Label(commandText);
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        boolean isExit;
        String output;
        try {
            Parser.parse(input);
            Command command = Parser.parse(input);
            output = command.execute(taskList, storage);
            isExit = command.isExit();
            if (isExit) {
                //this.stop();
            }
        } catch (DukeException de) {
            output = de.getMessage();
//            Alert alert = new Alert(Alert.AlertType.ERROR, de.getMessage());
        }
        return output;
    }

    private void loadTaskListFromFile(String filePath) throws DukeException {
        if (null == filePath || "".equals(filePath)) {
            filePath = "./duke.txt";
        }
        storage = new Storage(filePath);
        assert storage != null;

        taskList = new TaskList(storage.load());
        assert taskList != null;
    }

}
