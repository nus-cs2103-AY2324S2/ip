package baron;

import baron.Managers.TaskManager;
import baron.Models.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Baron extends Application {

    private TaskManager taskManager;
    public Baron() {
        this.taskManager = new TaskManager();
    }

//    public String getResponse(String input) {
////        String output = taskManager.handleInput(input);
//    }
//    public static void main(String[] args) {
//        System.out.println("Hello, I'm Baron. What can I do for you?");
//        Scanner scanner = new Scanner(System.in);
//
//        String input;
//        TaskManager taskManager = new TaskManager();
//        do {
//            input = scanner.nextLine();
//            taskManager.handleInput(input);
//        } while (!input.equals("bye"));
//
//        scanner.close();
//        System.out.println("Bye, good riddance");
//    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Baron.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = loader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    }
}
