package klee;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import klee.task.Task;

/**
 * Contains functions to output to the user.
 */
public class Ui {
    protected String divider = "____________________________________________________________________________"
            + "______________________________________";
    protected VBox dialogContainer;

    /**
     * Constructor for Ui class.
     */
    public Ui(VBox dialogContainer) {
        assert dialogContainer != null;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Welcome the user with a greeting.
     */
    public void showWelcome() {
        Label outputText = new Label();
        outputText.setAlignment(Pos.BASELINE_LEFT);
        outputText.setText("Hello! My name is Klee. "
                + "\nAre you here to break Klee out of solitary confinement?");
        outputText.setTranslateX(100);
        outputText.setTranslateY(25);
        Group output;

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_star_eyes.jpg_large"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Image paimonImage = new Image(getClass().getResourceAsStream("/images/Paimon_proud.png"));
        ImageView paimonView = new ImageView(paimonImage);
        paimonView.setX(485);
        paimonView.setY(0);
        paimonView.setPreserveRatio(true);
        paimonView.setFitHeight(100);

        output = new Group(kleeView, paimonView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Display the errorMessage given.
     *
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        Label outputText = new Label();
        outputText.setText(errorMessage);
        outputText.setTranslateX(120);
        outputText.setTranslateY(40);

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_thinking.jpg"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Group output = new Group(kleeView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * List out all the tasks given.
     *
     * @param tasks
     */

    public void showTasks(TaskList tasks) {
        Group outputText = new Group();
        Label outputLine = new Label();
        outputLine.setText("These are all the things that we have to do today:");
        outputText.getChildren().add(outputLine);
        outputText.setTranslateX(180);
        outputText.setTranslateY(0);

        for (int i = 0; i < tasks.size(); i++) {
            outputLine = new Label();
            outputLine.setText((i + 1) + ". " + tasks.get(i).getStatus());
            double offset = (1 + i) * 16;
            outputLine.setTranslateY(offset);
            outputText.getChildren().add(outputLine);
        }

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_drawing.jpg"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Group output = new Group(kleeView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Output all the status of the task that are in tasks.
     *
     * @param tasks
     */
    public void showFilteredTasks(TaskList tasks) {
        Group outputText = new Group();
        Label outputLine = new Label();
        outputLine.setText("These are all the things that are similar to what you are looking for:");
        outputText.getChildren().add(outputLine);
        outputText.setTranslateX(180);
        outputText.setTranslateY(0);

        for (int i = 0; i < tasks.size(); i++) {
            outputLine = new Label();
            outputLine.setText((i + 1) + ". " + tasks.get(i).getStatus());
            double offset = (1 + i) * 16;
            outputLine.setTranslateY(offset);
            outputText.getChildren().add(outputLine);
        }

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_drawing.jpg"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Group output = new Group(kleeView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Shows that the task was successfully created.
     *
     * @param task
     * @param size
     */
    public void showCreation(Task task, int size) {
        Label outputText = new Label();
        outputText.setText("Klee will help you write that down! : "
                + "\n" + task.getStatus()
                + "\n" + "Now you have " + size + " tasks in the list.");
        outputText.setTranslateX(180);
        outputText.setTranslateY(25);

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_drawing.jpg"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Group output = new Group(kleeView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Shows that the task was successfully marked.
     *
     * @param task
     */
    public void showMarked(Task task) {
        Label outputText = new Label();
        outputText.setText("Great! Klee will put a big cross on this box:"
                + "\n" + task.getStatus());
        outputText.setTranslateX(180);
        outputText.setTranslateY(25);

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_drawing.jpg"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Group output = new Group(kleeView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Shows that the task was successfully unmarked.
     *
     * @param task
     */
    public void showUnMarked(Task task) {
        Label outputText = new Label();
        outputText.setText("Oh no! Klee will burn the cross away...:"
                + "\n" + task.getStatus());
        outputText.setTranslateX(180);
        outputText.setTranslateY(25);

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_drawing.jpg"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Group output = new Group(kleeView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Shows that the task was successfully deleted.
     *
     * @param task
     * @param size
     */
    public void showDeletion(Task task, int size) {
        Label outputText = new Label();
        outputText.setText("Okay, Klee will wipe this task away!"
                + "\n" + task.getStatus()
                + "\nNow you have " + size + " tasks in the list.");
        outputText.setTranslateX(180);
        outputText.setTranslateY(25);

        Image kleeImage = new Image(getClass().getResourceAsStream("/images/Klee_drawing.jpg"));
        ImageView kleeView = new ImageView(kleeImage);
        kleeView.setX(0);
        kleeView.setY(0);
        kleeView.setPreserveRatio(true);
        kleeView.setFitHeight(100);

        Group output = new Group(kleeView, outputText);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Says goodbye to the user.
     */
    public void showBye() {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("Goodbye. Klee will go back to solitary confinement now...");
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }

    /**
     * Echo the user input.
     */
    public void echoUser(String userInput) {
        Label input = new Label();
        input.setText(userInput);
        input.setTranslateY(25);
        input.setTranslateX(25);

        Image paimonImage = new Image(getClass().getResourceAsStream("/images/Paimon_user.jpg"));
        ImageView paimonView = new ImageView(paimonImage);
        paimonView.setX(435);
        paimonView.setY(0);
        paimonView.setPreserveRatio(true);
        paimonView.setFitWidth(150);

        Group output = new Group(paimonView, input);
        dialogContainer.getChildren().add(output);

        Label dividerBlock = new Label();
        dividerBlock.setText(divider);
        dialogContainer.getChildren().add(dividerBlock);
    }
}
