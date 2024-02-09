package klee;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import klee.task.Task;

/**
 * Contains functions to output to the user.
 */
public class Ui {
    protected static String divider = "\n____________________________________________________________________________\n";
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
        //Greet user
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("Hello! My name is Klee. "
                + "\nAre you here to break Klee out of solitary confinement?"
                + divider);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Display the errorMessage given.
     *
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText(errorMessage + divider);
        dialogContainer.getChildren().add(output);
    }

    /**
     * List out all the tasks given.
     *
     * @param tasks
     */

    public void showTasks(TaskList tasks) {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("These are all the things that we have to do today:");
        output.autosize();
        dialogContainer.getChildren().add(output);
        for (int i = 0; i < tasks.size(); i++) {
            output = new Label();
            output.setAlignment(Pos.BASELINE_LEFT);
            output.setText((i + 1) + ". " + tasks.get(i).getStatus());
            output.autosize();
            dialogContainer.getChildren().add(output);
        }
        output = new Label();
        output.setText(divider);
        output.autosize();
        dialogContainer.getChildren().add(output);
    }

    /**
     * Output all the status of the task that are in tasks.
     *
     * @param tasks
     */
    public void showFilteredTasks(TaskList tasks) {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("These are all the things that are similar to what you are looking for:");
        output.autosize();
        dialogContainer.getChildren().add(output);
        for (int i = 0; i < tasks.size(); i++) {
            output = new Label();
            output.setAlignment(Pos.BASELINE_LEFT);
            output.setText((i + 1) + ". " + tasks.get(i).getStatus());
            output.autosize();
            dialogContainer.getChildren().add(output);
        }
        output = new Label();
        output.setText(divider);
        output.autosize();
        dialogContainer.getChildren().add(output);
    }

    /**
     * Shows that the task was successfully created.
     *
     * @param task
     * @param size
     */
    public void showCreation(Task task, int size) {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("Klee will help you write that down! : "
                + "\n" + task.getStatus()
                + "\n" + "Now you have " + size + " tasks in the list."
                + divider);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Shows that the task was successfully marked.
     *
     * @param task
     */
    public void showMarked(Task task) {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("Great! Klee will put a big cross on this box:"
                + "\n" + task.getStatus()
                + divider);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Shows that the task was successfully unmarked.
     *
     * @param task
     */
    public void showUnMarked(Task task) {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("Oh no! Klee will burn the cross away...:"
                + "\n" + task.getStatus()
                + divider);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Shows that the task was successfully deleted.
     *
     * @param task
     * @param size
     */
    public void showDeletion(Task task, int size) {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("Okay, Klee will wipe this task away!"
                + "\n" + task.getStatus()
                + "\nNow you have " + size + " tasks in the list."
                + divider);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Says goodbye to the user.
     */
    public void showBye() {
        Label output = new Label();
        output.setAlignment(Pos.BASELINE_LEFT);
        output.setText("Goodbye. Klee will go back to solitary confinement now..."
                + divider);
        dialogContainer.getChildren().add(output);
    }

    /**
     * Echo the user input.
     */
    public void echoUser(String userInput) {
        Label input = new Label();
        input.setAlignment(Pos.BASELINE_RIGHT);
        input.setText(userInput + divider);
        input.autosize();
        dialogContainer.getChildren().add(input);
    }
}
