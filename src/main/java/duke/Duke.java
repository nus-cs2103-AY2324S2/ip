package duke;
import exceptions.DukeException;
import exceptions.TaskNotExistException;
import model.Deadline;
import model.Event;
import model.Task;
import model.Todo;
import service.Parser;
import service.Storage;
import service.TaskList;
import ui.UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.DialogBox;

public class Duke {
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png")); //getresourceastream automatically starts us off at main/resources
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//
//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Snoopy");
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
//        //More functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }
//
//
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//        return textToAdd;
//    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, (user)),
//                DialogBox.getDukeDialog(dukeText, (duke))
//        );
//        userInput.clear();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = processCommand(input, taskList, true);
//        System.out.println("RESPONSE: " + response);
        return response;
    }


    public enum Command {
        Todo, Deadline, Event, Delete, Mark, Unmark, List, Bye, Unknown, Find;

        public static Command fromString(String maybeCommand) {
            try {
                return Command.valueOf(maybeCommand);
            } catch (Exception e) {
                return Unknown;
            }
        }
    }

    /**
     * Performs the appropriate action based on the user's input of the task.
     * From adding of tasks which can be todos, deadlines and events, deleting and marking and unmarking them.
     *
     * @param userInput user's commands
     * @param todos the list of tasks that are currently present.
     * @param isVerbose helps to ensure whether we are preloading (includes the need to save the entries)
     * @throws RuntimeException
     */
    public static String processCommand(String userInput, TaskList todos, Boolean isVerbose) throws RuntimeException {
        System.out.println("USERINPUT: " + userInput);
        String maybeCommand;
        String arr[];
        String reply = "";
        try {
            arr = userInput.split(" ", 2); // String in Array format. Useful: https://www.geeksforgeeks.org/split-string-java-examples/
            maybeCommand = arr[0];
        } catch (Exception e) {
            maybeCommand = null;
            return "null";
        }

        Command command = Command.fromString(maybeCommand);

        switch (command) {
        case Find:

            //add tasks with that keyword to a new arrayList, print items in that array list.
            if (isVerbose) {
                TaskList matchingTasks = new TaskList();
                String query = arr[1];
                for (int i = 0; i < todos.size(); i++) {
                    Task currTask = todos.get(i);
                    if (currTask.toString().contains(query)) {
                        matchingTasks.add(currTask);
                    }
                }

                ui.showLine();
                if (matchingTasks.size() > 0) {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        System.out.println((i + 1) + ". " + matchingTasks.get(i).toString());
                        reply += ((i + 1) + ". " + matchingTasks.get(i).toString());
                    }
                } else {
                    System.out.println(" Sorry no tasks found matching that word :<");
                    reply += (" Sorry no tasks found matching that word :<");
                }
                ui.showLine();
            }

            break;
        case Bye:
            reply += ui.formalities("farewell");
//            System.exit(0);
            break;
        case List:
            // list tasks
            if (isVerbose) {
                ui.showLine();
                System.out.println(" Here are the tasks in your list:");
                reply += " Here are the tasks in your list:";
            }
            for (int i = 0; i < todos.size(); i++) {
                Task currTask = todos.get(i);
                if (isVerbose) {
                    System.out.println((i + 1) + ". " + currTask.toString());
                    reply += (i + 1) + ". " + currTask.toString();
                }
            }
            if (isVerbose) {
                ui.showLine();
            }
            break;
        case Mark:
            // mark task as done
            Integer index = Integer.valueOf(arr[1]) - 1;
            Task currTask = todos.get(index);
            currTask.markAsDone();
            if (isVerbose) {
                System.out.print(" Nice! I've marked this task as done:\n");
                reply +=" Nice! I've marked this task as done:\n";
                System.out.println(" " + currTask.toString());
                reply += " " + currTask.toString();
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Unmark:
            // mark task as undone
            index = Integer.valueOf(arr[1]) - 1;
            if (isVerbose) {
                ui.showLine();
                System.out.print(" OK, I've marked this task as not done yet:\n");
                reply +=" OK, I've marked this task as not done yet:\n";
            }

            currTask = todos.get(index);
            currTask.markAsUndone();

            if (isVerbose) {
                System.out.println(" " + currTask.toString());
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Todo:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! The description of a todo cannot be empty.");
            }
            if (isVerbose) {
                ui.showLine();
                System.out.println("Got it. Added this task:");
            }
            Todo todo = new Todo(arr[1]);
            todos.add(todo);
            if (isVerbose) {
                System.out.println(todo.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Deadline:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! The description of a deadline cannot be empty.\nMake sure to add a deadline after the description with /by too!");
            }
            if (isVerbose) {
                ui.showLine();
                System.out.println("Got it. Added this task:");
            }
            String arguments[] = arr[1].split(" /by ");
            String description = arguments[0];
            String by = arguments[1];
            Deadline deadline = new Deadline(description, by);
            todos.add(deadline);
            if (isVerbose) {
                System.out.println(deadline.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Event:
            if (arr.length == 4) {
                throw new DukeException(" Nuh uh! The description of an event cannot be empty.\nMake sure to add a from and to date after the description with /from and /to too!");
            }
            if (isVerbose) {
                ui.showLine();
                System.out.println("Got it. Added this task:");
            }
            // extraction of parameters
            String getDesc[] = arr[1].split(" /from ");
            String desc = getDesc[0];
            String getDates[] = getDesc[1].split(" /to ");
            String from = getDates[0];
            String to = getDates[1];

            //creating of event
            Event event = new Event(desc, from, to);
            todos.add(event);

            if (isVerbose) {
                System.out.println(event.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        case Delete:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! Which task to delete? \nMake sure to add the task number!");
            }
            if (isVerbose) {
                ui.showLine();
            }
            Integer i = Integer.valueOf(arr[1]);
            Task task;
            try {
                task = todos.get(i - 1);
                todos.remove(i - 1);
            } catch (Exception e) {
                throw new TaskNotExistException(Integer.toString(i));
            }
            if (isVerbose) {
                System.out.println("Okay! I've fed this task to Woodstock, bye bye!:");
                System.out.println(task.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                ui.showLine();
                storage.updateRecords(taskList);
            }
            break;
        default:
            ui.showLine();
            System.out.println("Uh ah I don't understand ya ");
            ui.showLine();
            break;
        }
        return reply;
    }

    private static TaskList taskList;
    //private service.TaskList tasks;
    private static UI ui;
    private static Storage storage;

    /**
     * Constructor of Duke
     * @param filePath file storage location to save and retrieve list of tasks
     */
    public Duke(String filePath) {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage(filePath);
    }

    public Duke() {
       new Duke("./src/main/java/data/duke.txt");
    }

    /**
     * With a Duke object, .run() is the main entry point of the program, running all its processes.
     *
     */
    public void run(String input) {
        //do something
        ui.formalities("greet");

        //Load existing information
        try {
            storage.loadInfo(taskList);
            ui.showLine();
        } catch (Exception e) {
            e.getMessage();
            taskList = null;
        }
        while (true) {
            //Parsing user input.
            String command = new Parser().parse();

            //Process user command
            try {
                processCommand(command, taskList, true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ui.showLine();
            }
        }
    }

    /**
     * With a Duke object, .run() is the main entry point of the program, running all its processes.
     *
     */
    public void run() {
        //do something
        ui.formalities("greet");

        //Load existing information
        try {
            storage.loadInfo(taskList);
            ui.showLine();
        } catch (Exception e) {
            e.getMessage();
            taskList = null;
        }
        while (true) {
            //Parsing user input.
           String command = new Parser().parse();

            //Process user command
            try {
                processCommand(command, taskList, true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/data/duke.txt").run();
    }
}
