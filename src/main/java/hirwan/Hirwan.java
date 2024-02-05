package hirwan;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * The chatbots main class which simulates talking to a virtual assistant
 */
public class Hirwan extends Application {

    public Hirwan() {
    }


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Senddduuudududu");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Hirwan");
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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    static final String FILE_PATH =
            "C:\\Users\\eugen\\Documents\\National University of Singapore\\Y2S2\\CS2103T\\IP\\src\\main\\java\\data\\hirwan.txt";

    /**
     * The main method of the program. This method is the entry point
     * when the program is executed.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        Ui.output("Hello! " + logo);

        Tasklist tasks = new Tasklist(Storage.read());

        try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            while (true) {
                String text = Ui.input();
                int input = Parser.translate(text);

                if (input == 8) {
                    break;
                } else if (input == 1) {
                    tasks.printList();
                } else if (input == 2) {
                    try {
                        Ui.output("Got it. I've added this task: \n  " + "[T][ ] "
                                + text.substring(5));
                        tasks.add(". " + "[T][ ] " + text.substring(5));
                        Ui.output("Now you have " + tasks.size() + " tasks in the list.");
                        Storage.writeTask(tasks.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description for your todo command");
                    }
                } else if (input == 3) {
                    try {
                        String delimiter = " /by";
                        int index = text.indexOf(delimiter);
                        String Day = text.substring(index + 5);
                        String item = text.substring(9, index);

                        LocalDateTime dayDate = translateDate(Day);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        tasks.add(". " + "[D][ ] " + item + " (by: " + dayDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[D][ ] " + item + " (by: "
                                + dayDate.format(formatter) + ")");
                        Ui.output("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your deadline command");
                    }
                } else if (input == 4) {
                    try {
                        String delimiterstart = " /from";
                        String delimiterend = " /to";
                        int indexStart = text.indexOf(delimiterstart);
                        int indexEnd = text.indexOf(delimiterend);
                        String start = text.substring(indexStart + 7, indexEnd);
                        String end = text.substring(indexEnd + 5);
                        String item = text.substring(6, indexStart);

                        LocalDateTime startDate = translateDate(start);
                        LocalDateTime endDate = translateDate(end);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");

                        tasks.add(". " + "[E][ ] " + item + " (from: " + startDate.format(formatter) + " to: "
                                + endDate.format(formatter) + ")");
                        Ui.output("Got it. I've added this task:\n  " + "[E][ ] " + item + " (from: "
                                + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")");
                        Ui.output("Now you have " + tasks.size() + " tasks in the list.");
                        Storage.writeTask(tasks.getList());
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a description or date for your event to command");
                    }
                } else if (input == 5) {
                    try {
                        String number = text.substring(5);
                        int numberint = Integer.parseInt(number);
                        String temp = tasks.get(numberint - 1).substring(9);
                        String type = tasks.get(numberint - 1).substring(2, 5);

                        tasks.set(numberint - 1, ". " + type + "[X] " + temp);
                        Ui.output("Nice! I've marked this task as done: \n" + "[X] " + temp);
                        Storage.writeTask(tasks.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for marking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to mark!");
                    }
                } else if (input == 6) {
                    try {
                        String number = text.substring(7);
                        int numberInt = Integer.parseInt(number);
                        String temp = tasks.get(numberInt - 1).substring(9);
                        String type = tasks.get(numberInt - 1).substring(2, 5);

                        tasks.set(numberInt - 1, ". " + type + "[ ] " + temp);
                        Ui.output("OK, I've marked this task as not done yet: \n" + "[ ] " + temp);
                        Storage.writeTask(tasks.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for unmarking!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to unmark!");
                    }
                } else if (input == 7) {
                    try {
                        int numberInt = Integer.parseInt(text.substring(7)) - 1;
                        Ui.output("Noted. I've removed this task:\n"
                                + "  " + tasks.get(numberInt).substring(2) + "\n"
                                + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
                        tasks.delete(numberInt);
                        Storage.writeTask(tasks.getList());
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("Error: Please enter a valid index for deletion!");
                    } catch (NumberFormatException e) {
                        Ui.output("Error: Please enter a numerical index to delete!");
                    }
                } else if (input == 10) {
                    List<Integer> indexes = Hirwan.searchWord(text.substring(5), tasks.getList());
                    Hirwan.printSearchResults(indexes, tasks.getList());
                } else if (input == 9) {
                    Ui.output("Error: I am sorry but I do not recognise this command");
                }
            }
            Ui.output("Bye. Hope to see you again soon!");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Translates the string given into a LocalDateTime instance by taking in the string and formatting the date and
     * time then return the LocalDateTime instance
     *
     * @param date Date to be converted into LocalDateTime instance
     * @return The translated LocalDateTime instance
     */
    public static LocalDateTime translateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        LocalDateTime dateStored = LocalDateTime.parse(date, formatter);
        return dateStored;
    }

    /**
     * The method to search the tasks list to check if each element contains the search word
     *
     * @param word The search word to be found
     * @return the list of indexes of tasks that contain the search word
     */
    public static List<Integer> searchWord(String word, List<String> tasks) {
        List<Integer> indexes = new ArrayList<>();
        for (String element : tasks) {
            if (element.contains(word)) {
                indexes.add(tasks.indexOf(element));
            }
        }
        return indexes;
    }

    /**
     * The print search result method that prints the list of search results from the tasklist
     * that match the search term
     *
     * @param indexes the list of indexes of items in the tasklist that having matching substrings to the search term
     */
    public static void printSearchResults(List<Integer> indexes, List<String> tasks) {
        Ui.output("Here are the results of your search in the tasklist:\n");
        if (indexes.size() == 0) {
            Ui.output("There are no matching tasks with the search word!");
        } else {
            for (int index : indexes) {
                Ui.output((index + 1) + tasks.get(index));
            }
        }
    }
}
