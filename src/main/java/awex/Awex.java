package awex;

import java.io.IOException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TodoTask;

public class Awex {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /*
    // GUI TUT 2
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // GUI TUT 3
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image awex = new Image(this.getClass().getResourceAsStream("/images/awex.png"));
    */

    public Awex() {
        this.storage = new Storage("./list.txt");
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            ui.greeting();
            Parser.parse(this.tasks, this.ui);
            storage.store(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Awex().run();
    }

    // GUI
    /*
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
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
        stage.setTitle("Awex");
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

        //Step 3. Add functionality to handle user input.
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
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    /*
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    */

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label awexText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getAwexDialog(awexText, new ImageView(awex))
        );
        userInput.clear();
    }
    */

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String[] arr = input.split(" ", 2);
        if (input.equals("bye")) {
            return ui.farewell();
        } else if (input.equals("list")) {
            if (arr.length > 1) {
                return ui.allInstructions();
            } else if (tasks.isEmpty()) {
                return ui.emptyListMessage();
            } else {
                return ui.showListMessage(tasks);
            }
        } else if (arr[0].equals("find")) {
            if (arr.length < 2) {
                return ui.allInstructions();
            } else if (tasks.isEmpty()) {
                return ui.emptyListMessage();
            } else {
                return ui.showFindMessage(tasks, arr[1]);
            }
        } else if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
            String[] array = input.split(" ");
            if (array.length != 2) {
                return ui.wrongMarkDeleteFormatMessage(arr[0]);
            } else {
                int i = Integer.parseInt(array[1]);
                int len = tasks.size();
                if (i == 0 || i > len) {
                    return ui.wrongIndexMessage(i, len);
                } else {
                    if (arr[0].equals("delete")) {
                        return ui.deleteTaskMessage(i, tasks);
                    } else {
                        Task t = tasks.get(i - 1);
                        t.changeStatus(arr[0]);
                        return ui.changeStatusMessage(arr[0], t);
                    }
                }
            }
        } else {
            Task t;
            if (arr[0].equals("todo")) {
                if (arr.length == 1) {
                    return ui.failedTaskCreationMessage("todo");
                }
                t = TodoTask.of(arr[1]);
            } else if (arr[0].equals("deadline")) {
                String[] array = input.split("/");
                if (array.length != 2) {
                    return ui.failedTaskCreationMessage("deadline");
                }
                t = DeadlineTask.of(arr[1]);
            } else if (arr[0].equals("event")) {
                String[] array = input.split("/");
                if (array.length != 3) {
                    return ui.failedTaskCreationMessage("event");
                }
                t = EventTask.of(arr[1]);
            } else {
                return ui.allInstructions();
            }
            tasks.add(t);
            return ui.newTaskAddedMessage(tasks.size(), t);
        }
    }
}