import SnomStorage.TaskStorage;
import inputcommands.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import snomexceptions.InvalidCommandException;


import snomparser.Parser;
import snomtasklist.TaskList;
import snomui.Ui;


/**
 * Snom class implements the working body of SnomBot.
 */
public class Snom extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/trainer.png"));
    private Image snom = new Image(this.getClass().getResourceAsStream("/images/snom.png"));
    private Ui ui;
    private TaskStorage data;
    private TaskList lst;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Snom() {
        this.ui = new Ui();
        this.data = new TaskStorage();
        this.lst = TaskList.makeTaskList();
        this.parser = new Parser();

    }


    /**
     * Starts the execution of the Snom.
     */
//    public void runBot() {
//        this.ui.greet();
//        boolean isActive = true;
//        while (isActive) {
//            try {
//                String cmd = this.ui.getCommand();
//                Command command = Command.makeCommand(cmd);
//                isActive = this.parser.processCommand(command, this.lst, this.data);
//            } catch (InvalidCommandException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }

    public String runCommand(String s) {
        try {
            Command c = Command.makeCommand(s);
            return this.parser.processCommand(c, this.lst, this.data);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

    public void greet() {
        this.ui.greet();
    }



    @Override
    public void start(Stage stage) {

        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Snom");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        //You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });

    }

//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label snomText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText.toString(), new ImageView(user).getImage()),
//                DialogBox.getSnomDialog(snomText.toString(), new ImageView(snom).getImage())
//        );
//        userInput.clear();
//    }


    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


}
