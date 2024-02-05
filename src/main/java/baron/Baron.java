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

public class Baron {

    private TaskManager taskManager;
    public Baron() {
        this.taskManager = new TaskManager();
    }

    public String getResponse(String input) {
        String output = taskManager.handleInput(input);
        return output;
    }
}
