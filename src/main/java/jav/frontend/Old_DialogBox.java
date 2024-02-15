// package jav;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Node;
// import javafx.scene.control.Label;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.geometry.Pos;
// import javafx.scene.Node;
// import javafx.scene.control.Label;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;

// public class DialogBox extends HBox {

//     private Label text;
//     private ImageView displayPicture;

//     public DialogBox(Label l, ImageView iv, Color color) {
//         text = l;
//         displayPicture = iv;


//         this.setMargin(iv, new Insets(10 , 10, 10 , 10));
//         this.setMargin(l, new Insets(20 , 0, 20 , 0));

//         text.setWrapText(true);

//         double width = 100.0;
//         double height = 100.0;

//         displayPicture.setFitWidth(width);
//         displayPicture.setFitHeight(height);
//         displayPicture.setClip(new Circle(width / 2, height / 2, 50));

//         this.setBackground(new Background(new BackgroundFill(color, null, null)));
        
//         this.setAlignment(Pos.TOP_RIGHT);
//         this.getChildren().addAll(text, displayPicture);
//     }

//     /**
//      * Flips the dialog box such that the ImageView is on the left and text on the right.
//      */
//     private void flip() {
//         this.setAlignment(Pos.TOP_LEFT);
//         ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//         FXCollections.reverse(tmp);
//         this.getChildren().setAll(tmp);
//     }

//     public static DialogBox getUserDialog(Label l, ImageView iv) {
//         return new DialogBox(l, iv, new Color(1, 0.65, 0.65, 1));
//     }

//     public static DialogBox getDukeDialog(Label l, ImageView iv) {
//         var db = new DialogBox(l, iv, new Color(0.8, 0.8, 0.8, 1));
//         db.flip();
//         return db;
//     }
// }