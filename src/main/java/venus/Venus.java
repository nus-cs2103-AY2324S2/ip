package venus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is a deadline class that extends from the Task class.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Venus extends Application {

    private Storage storage;
    private TaskList taskList;

    /**
     * A venus used for Launcher in JavaFX.
     */
    public Venus() {
        this.storage = new Storage("data" + File.separator + "venus.txt");
        this.taskList = new TaskList();
        try {
            taskList.loadTasks(storage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File cannot be created or cannot be modified,"
                    + " please check file access modifiers!");
        }
    }

    /**
     * Returns Venus object with storage set as relative file path and empty taskList.
     *
     * @param filePath Relative file path of file.
     */
    public Venus(String filePath) {
        this.storage = new Storage("data" + File.separator + "venus.txt");
        this.taskList = new TaskList();
        try {
            taskList.loadTasks(storage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File cannot be created or cannot be modified,"
                    + " please check file access modifiers!");
        }
    }

    /**
     * Runs the program in the pre-level 10 style and exit if a "bye" input is detected.
     */
    public void run() {
        try {
            System.out.println(OldUi.getStart());
            taskList.loadTasks(storage);
            Scanner sc = new Scanner(System.in);
            String words = sc.nextLine().trim();
            while (!words.equals("bye")) {
                taskList.setTasks(words);
                words = sc.nextLine();
            }
            sc.close();
            taskList.saveTasks(storage);
        } catch (FileNotFoundException e) {
            OldUi.formatResponse("File cannot be created or cannot be modified,"
                    + " please check file access modifiers!");
        }
        System.out.println(OldUi.getEnd());
    }

    /**
     * Starts the program in PSVM.
     */
    public static void main(String[] args) {
        Venus venus = new Venus("data" + File.separator + "venus.txt");
        venus.run();
    }

    /**
     * Start used for JavaFX GUI.
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            String words = input.trim();
            if (!words.equals("bye")) {
                output = taskList.setTasksReturnMessage(words);
            } else {
                output = Gui.getEnd();
            }
            taskList.saveTasks(storage);
        } catch (FileNotFoundException e) {
            OldUi.formatResponse("File cannot be created or cannot be modified,"
                    + " please check file access modifiers!");
        }
        return output;
    }
}
