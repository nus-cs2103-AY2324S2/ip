package ken.control;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setFont(new Font(14));
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle clip = new Circle(displayPicture.getFitWidth() / 2, displayPicture.getFitHeight() / 2, Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2);
        displayPicture.setClip(clip);

        CornerRadii cornerRadii = new CornerRadii(80);
        Border border = new Border(new BorderStroke(Paint.valueOf("#F4F4F4"), BorderStrokeStyle.SOLID, cornerRadii, new BorderWidths(4)));

        // Apply the border to the HBox
        this.setBorder(border);

        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, cornerRadii, Insets.EMPTY)));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        //this.setStyle("-fx-background-color: #add8e6;");
        this.setPadding(new Insets(10, 10, 10, 10));
        HBox.setMargin(displayPicture, new Insets(0, 5, 0, 0));
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
//            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
//            fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        dialog.setText(text);
//        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }


    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getKenDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }


}

