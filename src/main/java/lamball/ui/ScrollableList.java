package lamball.ui;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Scrollable List component for the UI
 */
public class ScrollableList extends VBox {

    private ScrollPane scrollPane;
    private VBox dialogueContainer;

    /**
     * Constructor for ScrollableList.
     */
    public ScrollableList() {
        this.scrollPane = new ScrollPane();
        this.dialogueContainer = new VBox();
        this.scrollPane.setContent(dialogueContainer);
        this.init();
    }

    private void init() {
        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogueContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));
    }

    /**
     * Adds the nodes to the scrollable list.
     * @param n Nodes to add.
     */
    public void addToList(Node... n) {
        this.dialogueContainer.getChildren().addAll(n);
    }

    /**
     * Returns the given container.
     *
     * @return Dialogue container instance
     */
    public VBox getContainer() {
        return this.dialogueContainer;
    }

    /**
     * Returns the given scrollpane.
     *
     * @return Scrollpane instance
     */
    public ScrollPane getPane() {
        return this.scrollPane;
    }

}
