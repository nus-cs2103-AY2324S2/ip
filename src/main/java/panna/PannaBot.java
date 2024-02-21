package panna;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


// to add: tag for A-Classes
// to add: tag for A-Collections

/**
 * PannaBot is the main interface which runs the bot.
 * It comprises many features of a chatbot, including list, mark, unmark among others!
 */
public class PannaBot extends Application {
    private static Parser parser;
    private static String command = "";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));

    private Image panna = new Image(this.getClass().getResourceAsStream("/images/panna.jpg"));

    private Scene scene;

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private String filePath = "panna.txt";


    /**
     * Constructor to initialize all the elements.
     */
    public PannaBot() {

        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser("yyyy-MM-dd");
        this.taskList = new TaskList();

    }

    /**
     * This considers the user input and retrieves the response of the bot and displays them.
     * @throws PannaException
     */

    private void handleUserInput() throws PannaException {
        Label userText = new Label(userInput.getText());
        Label bugText = new Label(getResponse(userInput.getText()));
        userText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        bugText.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getPannaDialog(bugText, new ImageView(panna))
        );
        userInput.clear();
    }


    /**
     * Gets the response of the bot based on the human prompt.
     * @param input
     * @return String representing bot response.
     * @throws PannaException
     */

    private String getResponse(String input) throws PannaException {
        System.out.println(input);
        input = input.trim().toLowerCase();
        if (input.equals("list")) {
            String message = ui.listMessage(taskList);
            return message;
        } else if (input.startsWith("mark")) {
            String[] words = input.split(" ", 2);
            assert words.length <= 2;
            try {
                int i = Integer.parseInt(words[1]);


                ui.mark(taskList, i);
                String markMessage = ui.markDone(taskList, i);
                storage.write("panna.txt", taskList);
                return markMessage;
            } catch (Exception e) {
                return "I dont understand you!";
            }
        } else if (input.startsWith("unmark")) {
            String[] words = input.split(" ", 2);
            assert words.length <= 2;
            try {
                int i = Integer.parseInt(words[1]);

                ui.unmark(taskList, i);
                String unmarkMessage = ui.unmarkDone(taskList, i);
                storage.write("panna.txt", taskList);
                return unmarkMessage;
            } catch (Exception e) {
                return "I dont understand you!";
            }
        } else if (input.startsWith("event")) {
            String[] words = input.split(" ", 4);
            assert words.length <= 4;

            String newEvent = ui.event(taskList, words[1], parser.parse(words[2]), parser.parse(words[3]));
            storage.write("panna.txt", taskList);
            return newEvent;

        } else if (input.startsWith("todo")) {
            String[] words = input.split(" ", 2);
            assert words.length <= 2;
            String newToDo = ui.todo(taskList, words[1]);
            storage.write("panna.txt", taskList);
            return newToDo;

        } else if (input.startsWith("deadline")) {
            String[] words = input.split(" ", 3);
            assert words.length <= 3;
            String newDeadline = ui.deadline(taskList, words[1], parser.parse(words[2]));
            storage.write("panna.txt", taskList);
            return newDeadline;

        } else if (input.startsWith("delete")) {
            String[] words = input.split(" ", 2);
            assert words.length <= 2;
            try {
                int i = Integer.parseInt(words[1]);
                String deleteMessage = ui.delete(taskList, i);
                storage.write("panna.txt", taskList);
                return deleteMessage;
            } catch (Exception e) {
                return "Enter a valid integer!";
            }

        } else if (input.startsWith("find")) {
            String[] words = input.split(" ", 2);
            for (String s: words) {
                System.out.println(s);
            }
            System.out.println(words.length);

            String findMessage = ui.find(taskList, words[1]);
            storage.write("panna.txt", taskList);
            return findMessage;

        } else if (input.startsWith("update")) {
            String[] words = input.split(" ", 3);
            String updateMessage = ui.update(taskList, words[1], words[2]);
            storage.write("panna.txt", taskList);
            return updateMessage;
        } else {
            return "I dont understand you!";
        }
    }

    /**
     * Initializes the javafx scenes.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) throws PannaException {

        storage.read("panna.txt", taskList);
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setStyle("-fx-background-color:rgb(255, 253, 208);");
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        Scene scene = new Scene(mainLayout);
        mainLayout.setStyle("-fx-background-color:rgb(0, 0, 255);");

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        stage.setTitle("PannaBot");
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (PannaException e) {
                throw new RuntimeException(e);
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (PannaException e) {
                throw new RuntimeException(e);
            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Returns the label based on the text provided to it.
     * @param text
     * @return
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}




