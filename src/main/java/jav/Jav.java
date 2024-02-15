package jav;

import java.util.Scanner;

import jav.command.Command;
import jav.exception.InvalidCommandException;
import jav.exception.InvalidParamException;
import jav.manager.FileManager;
import jav.manager.ParserManager;
import jav.manager.StorageManager;
import jav.manager.UiManager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
* The main program.
*/
public class Jav extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    private static boolean isExiting = false;

    @Override
    public void start(Stage stage) {
    }

    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


    public Jav() {

    }

    // public static void main(String[] args) {
    //     // Print any neccessary UI upon starting up
    //     // Got ASCII Word Art from https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Jav
    //     String logo =
    //               "      ____.  _________   ____\n"
    //             + "     |    | /  _  \\   \\ /   /\n"
    //             + "     |    |/  /_\\  \\   Y   /\n"
    //             + " /\\__|    /    |    \\     /\n"
    //             + " \\________\\____|__  /\\___/\n"
    //             + "                  \\/\n"
    //             + "The Joy Amplifying Virtuoso!\n";

    //     System.out.println("Hello from\n" + logo);
    //     System.out.println("<---------------------------------------------------------->");
    //     UiManager.getInstance().printGreeting();

    //     // Load any neccessary data
    //     Scanner scan = new Scanner(System.in);
    //     try {
    //         StorageManager.getInstance().load(FileManager.getInstance().loadStorageData());
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }

    //     // Main program loop
    //     while (!isExiting) {
    //         // Get user input
    //         System.out.println("| User Input:");
    //         System.out.print("> ");
    //         String s = scan.nextLine();

    //         // Check if requested command can be found
    //         try {
    //             String cmd = s;
    //             String param = "";

    //             // Check if its a command with parameters
    //             if (s.indexOf(" ") != -1) {
    //                 cmd = s.substring(0, s.indexOf(' '));
    //                 param = s.substring(s.indexOf(' ') + 1);
    //             }

    //             // Parse and execute command
    //             Command c = ParserManager.getInstance().checkCommand(cmd, param);
    //             c.execute();
    //         } catch (InvalidCommandException e) {
    //             UiManager.getInstance().printInvalidCommand();
    //             UiManager.getInstance().echo(s);
    //         } catch (InvalidParamException e) {
    //             UiManager.getInstance().printInvalidParameters();
    //             UiManager.getInstance().echo(s);
    //         } catch (Exception e) {
    //             System.out.println(e.getMessage());
    //         }
    //     }

    //     UiManager.getInstance().printExit();
    //     System.out.println("<---------------------------------------------------------->");
    //     scan.close();
    // }

    public static void exit() {
        isExiting = true;
    }
}
