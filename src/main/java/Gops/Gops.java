package Gops;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Gops extends Application {
    private Ui ui;
    private TaskList taskList;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image gops = new Image(this.getClass().getResourceAsStream("/images/gops.png"));
    private ScrollPane scrollPane;
    private VBox chatBox;
    private TextField input;
    private Button button;
    private Scene scene;
    private Parser parser = new Parser();
    private File txtFile;
    /**
     * Constructor for Gops chatbot
     */
    public Gops() {
        ui = new Ui();
    }

    /**
     * Starts up the user interface
     */
    public void run() {
        this.taskList = new TaskList();
        int taskCount = 0;

        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        this.txtFile = new File(dataFolder,"Gops.Gops.txt");
        if (!txtFile.exists()) {
            try {
                this.txtFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            taskList = Storage.readFromHardDisk(txtFile);
            taskCount = taskList.numberOfTasks();
        }
    }

    @Override
    public void start(Stage stage) {
        assert stage != null : "Cannot start without a stage";
        run();

        scrollPane = new ScrollPane();
        chatBox = new VBox();
        scrollPane.setContent(chatBox);
        input = new TextField();
        button = new Button(">");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, input, button);
        scene = new Scene(mainLayout);
        stage.setScene(scene);

        Label welcomeMessage = new Label("Hello I'm Gops. How can I be of assistance");
        chatBox.getChildren().addAll(
                ChatBox.getDukeDialog(welcomeMessage, new ImageView(gops))
        );


        stage.show();

        stage.setTitle("Gops");
        stage.setResizable(false);
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        mainLayout.setPrefSize(500, 500);
        scrollPane.setPrefSize(500, 440);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        chatBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        input.setPrefWidth(500);
        button.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(button, 1.0);
        AnchorPane.setRightAnchor(button, 1.0);

        AnchorPane.setLeftAnchor(input , 1.0);
        AnchorPane.setBottomAnchor(input, 1.0);

        button.setOnMouseClicked((event) -> {
            chatBox.getChildren().add(getChatBubbleText(input.getText()));
            input.clear();
        });
        chatBox.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        button.setOnMouseClicked((event) -> {
            userInput();
        });

        input.setOnAction((event) -> {
            userInput();
        });

    }

    private Label getChatBubbleText(String string) {
        Label textLabel = new Label(string);
        textLabel.setWrapText(true);
        return textLabel;
    }

    private void userInput() {
        Label userText = new Label(input.getText());
        Label dukeText = new Label(getResponse(input.getText()));
        chatBox.getChildren().addAll(
                ChatBox.getUserDialog(userText, new ImageView(user)),
                ChatBox.getDukeDialog(dukeText, new ImageView(gops))
        );
        input.clear();
    }
    private String getResponse(String input) {
        assert input != null : "getResponse cannot respond without an input";
        return parser.parse(input, this.taskList, this.txtFile);
    }

    /**
     * Start running Gops chatbot
     * @param args args for main
     */
    public static void main(String[] args) {
        launch(args);
    }
}