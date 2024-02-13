package BadApple.main;

import BadApple.task.Parser;
import BadApple.task.Storage;
import BadApple.task.TaskList;
import BadApple.task.Tracker;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.application.Application;

public class BadPingGuo extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        Ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String filename = "src/main/data/whiteSpace.txt";

        try {
            File file = new File(filename);
            FileReader fc = new FileReader(file);
            BufferedReader reader = new BufferedReader(fc);

            Tracker.suppressMessages = true;
            Storage.parseTasks(file);
            Tracker.suppressMessages = false;

            TaskList.listTasks(reader);
            System.out.println("Waiting for something to happen?");

            // self note: update the file everytime an operation is complete.
            // Delete the old file, for loop all the tasks into new file. Rename it to old file name.
            while(true) {
                String request = sc.nextLine();
                if (request.equalsIgnoreCase("bye")) break;
                Parser.ProcessQuery(request, file);
            }

            System.out.println("--------------------------------");
            System.out.println("Everything is going to be okay.");

            fc.close();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("You've been living here for as long as... wait, no headspace detected?");
            System.out.println("Would you like to enter White Space? \n" +
                    "Only 'yes' will create the required files" );
            String askToCreateFile = sc.nextLine();
            if (askToCreateFile.equalsIgnoreCase("yes")) {
                makeFile();
            }
        } catch (IOException e) {
            System.out.println("unable to process file");
        } catch (BadAppleException be) {
            System.out.println(be.toString());
        }

    }

    public static boolean makeFile() {
        try {
            File f1 = new File("src/main/data");
            File f = new File("src/main/data/whiteSpace.txt");
            return f1.mkdir() && f.createNewFile();
        } catch (IOException e) {
            System.out.println("Humphrey has denied your entrance to white space! \n " +
                    "perhaps the write permissions aren't working?");
            return false;
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

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

        //More code to be added here later
    }

}
