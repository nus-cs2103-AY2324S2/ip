package paimon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import paimon.controller.ChatController;


/**
 * Main Class
 **/
public class Main extends Application {
    //This is our PrimaryStage (It contains everything)
    private Stage primaryStage;
    //This is the BorderPane of RootLayout
    private BorderPane rootLayout;
    private final DialogHandler dialogHandler;
    public Main() {
        this.dialogHandler = new DialogHandler();
    }

    @Override
    public void start(Stage primaryStage) {
        //1) Declare a primary stage (Everything will be on this stage)
        this.primaryStage = primaryStage;

        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("Paimon");

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the EmployeeOperations View
        showChatView();
    }

    /**
     * Initializes Root layout
     **/
    public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            this.rootLayout = loader.load();

            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            //Third, show the primary stage
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads Chat View and sets it to Center of Stage
     **/
    public void showChatView() {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/ChatView.fxml"));
            AnchorPane chatView = loader.load();

            // Set Chat view into the center of root layout.
            this.rootLayout.setCenter(chatView);

            ChatController controller = loader.getController();
            controller.setDialogHandler(this.dialogHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
