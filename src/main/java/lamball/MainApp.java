package lamball;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lamball.ui.CommandTextField;
import lamball.ui.DialogBox;
import lamball.ui.ScrollableList;


/**
 * MainApp to launch GUI.
 */
public class MainApp extends Application {
    private Image lamballImg = new Image(this.getClass().getResourceAsStream("/images/Lamball.png"));
    private ScrollableList scrollList;
    private Scene scene;
    private Lamball lamb;

    private CommandTextField sender;

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        lamb = new Lamball();
        String initialDisplayText = lamb.initialize();

        scrollList = new ScrollableList();
        sender = new CommandTextField(scrollList, lamb);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollList.getPane(), sender);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Lamball");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // mainLayout.setPrefSize(400.0, 600.0);

        AnchorPane.setTopAnchor(scrollList, 1.0);
        AnchorPane.setBottomAnchor(sender, 1.0);

        scrollList.addToList(
                DialogBox.getLamballDialog(
                        new Text(initialDisplayText),
                        new ImageView(lamballImg), 400)
        );

    }

}
