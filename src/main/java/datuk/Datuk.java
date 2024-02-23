package datuk;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This Duke program is a chatbot that takes handles a list for the user.
 */

public class Datuk extends Application {

    private UI ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image datuk = new Image(this.getClass().getResourceAsStream("/images/Datuk.jpg"));

    public Datuk() throws IOException {
        ui = new UI();
        storage = new Storage();
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (NullPointerException e) {
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
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

        stage.setTitle("Datuk \uD83D\uDC74");
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

        Label start = new Label(ui.startMsg(tasks.reminder()));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(start, new ImageView(datuk)));

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


    }

    /**
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label datukText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(datukText, new ImageView(datuk))
        );
        userInput.clear();
    }

    /**
     * Passes user input into the parser and executes the task.
     *
     * @param text user input.
     * @return chatbot response.
     */
    public String getResponse(String text) {
        String out;
        String cmd = parser.parseCmd(text);
        try {
            if (cmd.equals("bye")) {
                out = ui.byeMsg();
                exit();
            } else if (cmd.equals("list")) {
                out = ui.printList(tasks.get());
            } else if (cmd.equals("mark") || cmd.equals("unmark")) {
                out = tasks.marked(parser.parseMark(text));
                storage.saveTasks(tasks.get());
            } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")){
                out = tasks.addItem(parser.parseAdd(text));
                storage.saveTasks(tasks.get());
            } else if (cmd.equals("delete")) {
                out = tasks.deleteItem(parser.parseDelete(text));
                storage.saveTasks(tasks.get());
            } else if (cmd.equals("find")) {
                out = tasks.findItem(parser.parseFind(text));
            } else {
                throw new DatukException("Your input is invalid!");
            }
        } catch (DatukException de) {
            out = ui.showError(de);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out;
    }

    private void exit()  {
        ScheduledExecutorService execServ = Executors.newSingleThreadScheduledExecutor();

        execServ.scheduleWithFixedDelay(() -> {
            Platform.exit(); //exit javaFx
            System.exit(0); //exit program
        }, 1, 3, TimeUnit.SECONDS);
    }
}

