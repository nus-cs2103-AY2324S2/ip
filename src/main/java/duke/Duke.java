package duke;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Duke extends Application {

    private Storage storage;
    private TaskList todo;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Dwight_Schrute.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Michael_Scott.png"));

    // Duke Constructor
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            todo = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            todo = new TaskList();
        }
    }


    public void run() {
        String divider = "---------------------------------------------------------------";
        Scanner scanner = new Scanner(System.in);
        String input;
        String command;
        String keyword;
        Task task;
        int idx;
        ui.welcome();
        ui.showCommands();
        ui.divider();
        while (scanner.hasNext()) {
            try {
                input = scanner.nextLine();
                System.out.printf("\t%s\n", divider);
                command = Parser.parse(input);
                switch (command) {
                    case ("bye"):
                        System.out.println("\tBye. Hope to see you again soon!");
                        scanner.close();
                        return;
                    case ("list"):
                        this.todo.list();
                        break;
                    case("mark"):
                        try {
                            idx = Parser.parse_mark(input);
                            task = todo.mark(idx);
                            System.out.println("\tNice! I've marked this task as done:");
                            System.out.printf("\t\t%s\n", task);
                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }
                    case("unmark"):
                        try {
                            idx = Parser.parse_unmark(input);
                            task = todo.unmark(idx);
                            System.out.println("\tI've unmarked this task as done:");
                            System.out.printf("\t\t%s\n", task);
                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }
                    case ("todo"):

                        try {
                            // calling the method
                            task = Parser.parse_todo(input);
                            todo.addTask(task);
                            System.out.println("\tGot it. I've added this task:");
                            System.out.printf("\t\t%s\n", task);
                            System.out.printf("\tNow you have %d tasks in the list.\n", todo.size());

                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }

                    case ("deadline"):

                        try {
                            task = Parser.parse_deadline(input);
                            todo.addTask(task);
                            System.out.println("\tGot it. I've added this task:");
                            System.out.printf("\t\t%s\n", task);
                            System.out.printf("\tNow you have %d tasks in the list.\n", todo.size());

                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        } catch (DateTimeParseException err) {
                            System.out.println("\tPlease write your data in d/m/yyyy T format");
                            break;
                        }
                    case ("event"):

                        try {
                            task = Parser.parse_event(input);
                            todo.addTask(task);
                            System.out.println("\tGot it. I've added this task:");
                            System.out.printf("\t\t%s\n", task);
                            System.out.printf("\tNow you have %d tasks in the list.\n", todo.size());

                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }

                    case("delete"):
                        try {
                            idx = Parser.parse_delete(input);
                            System.out.println("\tNoted. I've removed this task:");
                            System.out.printf("\t\t%s\n", todo.deleteTask(idx));
                            System.out.printf("\tNow you have %d tasks in the list.\n", todo.size());
                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }
                    case("find"):
                        try {
                            keyword = Parser.parse_find(input);
                            System.out.println("\tHere are the matching tasks in your list:");
                            todo.find(keyword);
                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }

                    default:
                        throw new DukeException("\tSorry, I did not understand the command!");

                }
                System.out.printf("\t%s\n", divider);
                storage.writeFile(todo.getList());
            } catch (DukeException err) {
                System.out.println(err.getMessage());
                System.out.printf("\t%s\n", divider);
            } catch (IOException err) {
                System.out.println(err.getMessage());
                System.out.printf("\t%s\n", divider);
            }
        }
    }

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

        // Step 2.
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

        // Step 3.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String responseString = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(new Label(responseString), new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        StringBuilder responseString = new StringBuilder();
        String command;
        String keyword;
        Task task;
        int idx;

        try {

            command = Parser.parse(input);
            switch (command) {
                case ("bye"):
                    responseString.append("\tBye. Hope to see you again soon!");
                    Platform.exit();
                    System.exit(0);
                case ("list"):
                    for (int i = 0; i < this.todo.getList().size(); i++) {
                        responseString.append(String.format("\t%d. %s", i + 1, this.todo.getList().get(i)));
                    }
                    break;
                case("mark"):
                    try {
                        idx = Parser.parse_mark(input);
                        task = todo.mark(idx);
                        responseString.append("\tNice! I've marked this task as done:");
                        responseString.append(String.format("\t\t%s\n", task));
                        break;
                    } catch (DukeException err) {
                        responseString.append(err.getMessage());
                        break;
                    }
                case("unmark"):
                    try {
                        idx = Parser.parse_unmark(input);
                        task = todo.unmark(idx);
                        responseString.append("\tI've unmarked this task as done:");
                        responseString.append(String.format("\t\t%s\n", task));

                        break;
                    } catch (DukeException err) {
                        responseString.append(err.getMessage());
                        break;
                    }
                case ("todo"):

                    try {
                        // calling the method
                        task = Parser.parse_todo(input);
                        todo.addTask(task);
                        responseString.append("\tGot it. I've added this task:");
                        responseString.append(String.format("\t\t%s\n", task));
                        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));

                        break;
                    } catch (DukeException err) {
                        responseString.append(err.getMessage());
                        break;
                    }

                case ("deadline"):

                    try {
                        task = Parser.parse_deadline(input);
                        todo.addTask(task);
                        responseString.append("\tGot it. I've added this task:");
                        responseString.append(String.format("\t\t%s\n", task));
                        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));

                        break;
                    } catch (DukeException err) {
                        responseString.append(err.getMessage());
                        break;
                    } catch (DateTimeParseException err) {
                        responseString.append("\tPlease write your data in d/m/yyyy T format");
                        break;
                    }
                case ("event"):

                    try {
                        task = Parser.parse_event(input);
                        todo.addTask(task);
                        responseString.append("\tGot it. I've added this task:");
                        responseString.append(String.format("\t\t%s\n", task));
                        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));
                        break;
                    } catch (DukeException err) {
                        responseString.append(err.getMessage());
                        break;
                    }

                case("delete"):
                    try {
                        idx = Parser.parse_delete(input);
                        responseString.append("\tNoted. I've removed this task:");
                        responseString.append(String.format("\t\t%s\n", todo.deleteTask(idx)));
                        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));
                        break;
                    } catch (DukeException err) {
                        responseString.append(err.getMessage());
                        break;
                    }
                case("find"):
                    try {
                        keyword = Parser.parse_find(input);
                        for (int i = 0; i < todo.size(); i++) {
                            if (todo.getList().get(i).getName().contains(keyword)) {
                                responseString.append(String.format("\t%d. %s", i + 1, todo.getList().get(i)));
                            }
                        }
                        responseString.append("\tHere are the matching tasks in your list:");
                        break;
                    } catch (DukeException err) {
                        responseString.append(err.getMessage());
                        break;
                    }

                default:
                    throw new DukeException("\tSorry, I did not understand the command!");

            }

            storage.writeFile(todo.getList());
        } catch (DukeException | IOException err) {
            responseString.append(err.getMessage());

        }
        return responseString.toString();
    }
//    public static void main(String[] args) throws IOException, DukeException {
//        new Duke().run();
//    }
}
