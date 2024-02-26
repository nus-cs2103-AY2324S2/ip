package damon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;




public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        //Solution below inspired by https://stackoverflow.com/questions/20708295/put-a-image-in-a-circle-view
        displayPicture.setClip(new Circle(50, 50, 50));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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
        DialogBox userDialogBox = new DialogBox(l, iv);
        //Solution below inspired by http://www.java2s.com/Code/Java/JavaFX/SetPaddingandSpacing.htm
        userDialogBox.setPadding(new Insets(10, 10, 10, 15));
        return userDialogBox;
    }

    public static DialogBox getDamonDialog(Label l, ImageView iv) {
        var damonDialogBox = new DialogBox(l, iv);
        //Solution below inspired by http://www.java2s.com/Code/Java/JavaFX/SetPaddingandSpacing.htm
        damonDialogBox.setPadding(new Insets(10, 15, 10, 10));

        damonDialogBox.flip();
        return damonDialogBox;
    }

}

