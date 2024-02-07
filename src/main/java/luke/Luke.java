package luke;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Luke extends Application {

    //Used this https://se-education.org/guides/tutorials/javaFxPart2.html as the main template!
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/roblox.jpg"));
    private Image luke = new Image(this.getClass().getResourceAsStream("/images/ltg.jpg"));

    public static void main(String[] args) {
        launch();
    }

    //Used this https://se-education.org/guides/tutorials/javaFxPart2.html as the main template!
    @Override
    public void start(Stage stage) throws Exception {
        Storage storage;
        UI ui = new UI();

        String wd = System.getProperty("user.dir");
        Path directoryPath = Paths.get(wd,  "data");
        Path historyPath = Paths.get(wd, "data", "history.txt");
        //create directory (if it does not already exist)
        try {
            Files.createDirectory(directoryPath);
        } catch (FileAlreadyExistsException e) {
            //directory already exists.
        } catch (IOException e) {
            System.out.println("Failed to create directory");
        }
        //create file (if it does not already exist)
        try {
            Files.createFile(historyPath);
        } catch (FileAlreadyExistsException e) {
            //file already exists.
        } catch (IOException e) {
            System.out.println("Failed to create file");
            return;
        }

        //load the file if there is save data (reference: https://www.baeldung.com/java-serialization)
        //otherwise, create a new History object.
        File historyFile = new File(String.valueOf(historyPath));
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(historyFile));
            storage = (Storage) inputStream.readObject();
        } catch (IOException e) {
            //System.out.println("No save data found, creating new save.");
            storage = new Storage();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            return;
        }

        Parser parser = new Parser(storage, historyFile);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setX(300);
        stage.setY(100);
        stage.show();

        stage.setTitle("Luke");
        double minHeight = 600;
        double minWidth = 600;
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);
        stage.setResizable(false);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(0.97 * minWidth, 0.89 * minHeight);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(520);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        lukeSpeaks(UI.greet());

        //makes it such that we handle user input upon clicking.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(parser, ui, stage);
        });

        //makes it such that we handle user input upon pressing enter.
        userInput.setOnAction((event) -> {
            handleUserInput(parser, ui, stage);
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private void handleUserInput(Parser parser, UI ui, Stage stage) {
        String formattedInput = userInput.getText();
        String lukeReply;
        Label lukeText;
        Label userText = new Label(userInput.getText());
        if (parser.isLastCommand) {
            stage.close();
        }
        try {
            lukeReply = parser.parseCommand(formattedInput);
        } catch (TasklistException e) {
            lukeReply = e.getMessage();
        } catch (ParseCommandException e) {
            lukeReply = e.getMessage();
        }
        if (lukeReply.equals("QUIT")) {
            lukeText = new Label(ui.bye());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getLukeDialog(lukeText, new ImageView(luke))
            );
            parser.isLastCommand = true;
        } else {
            lukeText = new Label(lukeReply);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getLukeDialog(lukeText, new ImageView(luke))
            );
            userInput.clear();
        }
    }

    private void lukeSpeaks(String input) {
        Label lukeText = new Label(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getLukeDialog(lukeText, new ImageView(luke))
        );
    }

}


