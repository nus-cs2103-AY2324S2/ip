package signal;


import signal.task.Task;

import signal.util.Parser;
import signal.util.Storage;
import signal.util.Ui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Duke {


    private static Storage fileManager = new Storage();
    private static ArrayList<Task> taskList = fileManager.loadTasks();
    private static Ui ui = new Ui(taskList, fileManager);
    public static Parser parser = new Parser(taskList, ui);

    public Duke() {

    }


    public static void main(String[] args) {
        ui.intro();


        while(true) {
            String userInput = ui.scan();

            if (userInput.equals("bye")) {
                // Exit program
                break;
            }
            parser.read(userInput);
        }
        ui.leave();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Signal heard: " + input;
    }

    public String getIntro() {
        return ui.intro();
    }


}

