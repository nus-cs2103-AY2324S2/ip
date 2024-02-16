package lamball;

import static javafx.application.Platform.exit;

import java.util.Scanner;

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
import lamball.command.Command;
import lamball.memo.Memo;
import lamball.memo.MemoList;
import lamball.ui.DialogBox;

/**
 * Main chat bot class that is tasked with initializing and relaying user inputs to the various classes.
 *
 * @author ongzhili
 */
public class Lamball extends Application {
    private static final String USER_PROMPT = "    You:";
    private static final String EXIT_TEXT = "     See you again!\n";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Lamball.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private TaskList tasks;
    private MemoList memos;
    private Ui ui;
    private String lastDoneTask;


    /**
     * Constructor for Lamball chatbot.
     *
     */
    public Lamball() {
        tasks = new TaskList(this);
        memos = new MemoList(this);
        ui = new Ui();
    }

    private String initialize() {
        ui.greetingMessage();
        String guiText = Storage.obtainSavedFile(this);
        return guiText;
    }

    public void addToMemList(Memo mem, boolean isInit) {
        this.memos.addMemo(mem, isInit);
    }

    /**
     * Parse for initial list of commands
     *
     * @param msg Command to parse.
     * @throws LamballParseException if invalid command is provided.
     */
    public void initParse(String msg) throws LamballParseException {
        Command parsed = Parser.parse(msg, tasks, memos, true);
        parsed.run();
    }

    /**
     * Runs Lamball chatbot.
     *
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;

        this.initialize();

        while (isActive) {
            System.out.print(USER_PROMPT);
            String userInput = scanner.nextLine();

            // Echo the user's command
            try {
                Command comd = Parser.parse(userInput, tasks, memos, false);
                isActive = comd.run();
                if (!isActive) {
                    ui.goodbyeMessage();
                } else {
                    ui.displayAction(this.lastDoneTask);
                }
            } catch (LamballParseException e) {
                ui.displayError(e);
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Lamball().run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        String initialDisplayText = this.initialize();

        //The container for the content of the chat to scroll.
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
        stage.setTitle("Lamball");
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

        dialogContainer.getChildren().add(
                DialogBox.getLamballDialog(
                        new Label(initialDisplayText),
                        new ImageView(duke))
        );

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label lamballText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getLamballDialog(lamballText, new ImageView(duke))
        );

        if (lamballText.getText() == EXIT_TEXT) {
            exit();
        }
        userInput.clear();
    }

    /**
     * Generates response to user input.
     *
     * @return Responds appropriately to given input.
     */
    private String getResponse(String input) {
        String response = "";
        try {
            Command comd = Parser.parse(input, tasks, memos, false);
            boolean isActive = comd.run();
            if (!isActive) {
                response = ui.goodbyeMessage();
            } else {
                response = ui.displayAction(this.lastDoneTask);
            }
        } catch (LamballParseException e) {
            response = ui.displayError(e);
        }
        assert response != "" : "response should not be empty";
        return response;
    }

    /**
     * Updates last done task (either in memo or tasklist)
     *
     * @param task Formatted string of last done task.
     */
    public void updateLastDoneTask(String task) {
        this.lastDoneTask = task;
    }
}


