package duke;

import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Timer;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.application.Platform;
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


public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.jpg")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/louie.jpg")));

    private final Ui ui = new Ui();
    private final CommandList commands = new CommandList();
    private TaskList tasks = new TaskList();
    private final Storage st = new Storage("data.txt");


    public Duke() {
    }

    public void addCommand(String name, Consumer<Parser> executor) {
        commands.put(name, new Command(name, executor));
    }

    /**
     * Prints message to the chatbot GUI.
     * @param msg the message to be printed.
     */
    public void print(String msg) {
        dialogContainer.getChildren()
                .add(DialogBox.createDukeDialog
                        (new Label(msg), new ImageView(duke)));

    }

    private void initCommands() {
        // initialise commands
        this.addCommand("list", (args) -> {
            try {
                args.assertEnd();
                this.print("Here's what you've done today...\n" + this.tasks.toDisplayString());
                
            } catch (DukeOptionParsingException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        this.addCommand("bye", (args) -> {
            try {
                args.assertEnd();
                this.print("Ok, going to sleep...");
                Platform.exit();
            } catch (DukeOptionParsingException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        this.addCommand("mark", (args) -> {
            try {
                int index;
                Task t;

                {
                    String indexStr = args.next();
                    try {
                        index = Integer.parseInt(indexStr);
                    } catch (NumberFormatException e) {
                        throw new DukeOptionParsingException
                                (String.format("I expected a number but %s was given instead", indexStr));
                    }
                }
                
                args.assertEnd();
                
                try { 
                    t = this.tasks.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(String.format("You tried to access an invalid task index: %d", index));
                }
                
                t.mark();
                this.print("CONGRATULATION!!!!!! you completed this task:\n" + t.describe());
                this.st.writeTasks(this.tasks);
            } catch (DukeException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        this.addCommand("unmark", (args) -> {
            try {
                int index;
                Task t;

                {
                    String indexStr = args.next();
                    try {
                        index = Integer.parseInt(indexStr);
                    } catch (NumberFormatException e) {
                        throw new DukeOptionParsingException(
                                String.format("I expected a number but %s was given instead", indexStr)
                        );
                    }
                }
                
                args.assertEnd();
                
                try { 
                    t = this.tasks.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException
                            (String.format("You tried to access an invalid task index: %d", index));
                }
                
                t.unmark();
                this.print("CONGRATULATION!!!!!! you un completed this task:\n" + t.describe());
                this.st.writeTasks(this.tasks);
            } catch (DukeException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        this.addCommand("todo", (args) -> {
            
            try {
                String str = args.rest();
                Task t = new ToDo(str);
                this.print(String.format("Ok, I've added a new todo...\n  %s", t.describe()));
                this.tasks.add(t);
                this.st.writeTasks(this.tasks);
            } catch (DukeException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        this.addCommand("deadline", (args) -> {
            StringBuilder by = new StringBuilder();
            StringBuilder name = new StringBuilder();
            Task t;
            
            final String NO_NAME = "you didn't specify specify a name for your deadline";
            final String NO_BY = "you failed to specify an end date using '/by'";
            
            try {
                while (!args.peek().startsWith("/")) {
                    if (!name.isEmpty()) {
                        name.append(" ");
                    }
                    name.append(args.next());
                }

                if (name.isEmpty()) {
                    throw new DukeOptionParsingException(NO_NAME);
                }

                {
                    String str = args.next();
                    if (!str.equals("/by")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", str));
                    }
                }

                while (args.hasNext()) {
                    String next = args.next();
                    if (next.startsWith("/")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", next));
                    }
                    if (!by.isEmpty()) {
                        by.append(" ");
                    }
                    by.append(next);
                }

                if (by.isEmpty()) {
                    throw new DukeOptionParsingException(NO_BY);
                }
                
                try {
                    t = new Deadline(name.toString(), by.toString());
                } catch (DateTimeParseException e) {
                    throw new DukeException("Couldn't parse the end date " + by);
                }
                
                this.print(String.format("Ok, I've added a new deadline...\n  %s", t.describe()));
                this.tasks.add(t);
                this.st.writeTasks(this.tasks);
            } catch (DukeException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
            
            
        });

        this.addCommand("event", (args) -> {
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            StringBuilder name = new StringBuilder();
            Event t;


            final String NO_NAME = "you didn't specify specify a name for your event";
            final String NO_FROM = "you failed to specify an start date using '/from'";
            final String NO_TO = "you failed to specify an end date using '/to'";

            try {
                while (!args.peek().startsWith("/")) {
                    if (!name.isEmpty()) {
                        name.append(" ");
                    }
                    name.append(args.next());
                }

                if (name.isEmpty()) {
                    throw new DukeOptionParsingException(NO_NAME);
                }

                {
                    String str = args.next();
                    if (!str.equals("/from")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", str));
                    }
                }


                while (!args.peek().startsWith("/")) {
                    if (!from.isEmpty()) {
                        from.append(" ");
                    }
                    from.append(args.next());
                }

                if (from.isEmpty()) {
                    throw new DukeOptionParsingException(NO_FROM);
                }
                
                {
                    String str = args.next();
                    if (!str.equals("/to")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", str));
                    }
                }

                while (args.hasNext()) {
                    String next = args.next();
                    if (next.startsWith("/")) {
                        throw new DukeOptionParsingException
                                (String.format("I encountered an unexpected option '%s'", next));
                    }
                    if (!to.isEmpty()) {
                        to.append(" ");
                    }
                    to.append(next);
                }

                if (to.isEmpty()) {
                    throw new DukeOptionParsingException(NO_TO);
                }
                
                try {
                    t = new Event(name.toString(), from.toString(), to.toString());
                } catch (DateTimeParseException e) {
                    throw new DukeException(String.format
                            ("Couldn't parse the start/end date %s/%s", from, to));
                }

                this.print(String.format("Ok, I've added a new event...\n  %s", t.describe()));
                this.tasks.add(t);
                this.st.writeTasks(this.tasks);
            } catch (DukeException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        this.addCommand("delete", (args) -> {
            try {
                int index;
                Task t;

                {
                    String indexStr = args.next();
                    try {
                        index = Integer.parseInt(indexStr);
                    } catch (NumberFormatException e) {
                        throw new DukeOptionParsingException(
                                String.format("I expected a number but %s was given instead", indexStr)
                        );
                    }
                }

                args.assertEnd();

                try {
                    t = this.tasks.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException
                            (String.format("You tried to access an invalid task index: %d", index));
                }
                this.tasks.remove(index);
                this.print("I'm deleting this task. bye...\n" + t.describe());
                this.st.writeTasks(this.tasks);
            } catch (DukeException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });

        this.addCommand("find", (psr) -> {
            try {
                String toFind = psr.rest();
                this.print(String.format("I found the following tasks with names that match '%s':\n%s", 
                        toFind, 
                        this.tasks.filterSubString(toFind)));
            } catch (DukeOptionParsingException e) {
                this.print("OH NYO ERROR!!!!!!!!!!!!! " + e.getMessage());
            }
        });
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

        stage.setScene(scene);
        stage.show();

        //More code to be added here later
        //Step 2. Formatting the window to look as expected
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        try {
            this.tasks = this.st.loadTasks();
        } catch (DukeException e) {
            this.print(String.format
                    ("Error loading task data: %s"
                            + "\n\nPlease delete 'data.txt' and try again. Bye bye...", e.getMessage()));
            System.exit(1);
        }
        
        this.initCommands();
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().add(DialogBox.createUserDialog(userText, new ImageView(user)));
        Parser parser = new Parser(userInput.getText());
        userInput.clear();
        
        try {
            this.commands.get(parser.next()).run(parser);
        } catch (DukeCommandNotFoundException | DukeOptionParsingException e) {
            this.print("no matching command...");
        }
    }
}
