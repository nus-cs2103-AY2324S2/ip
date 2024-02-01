package seedu.chatteroo.ui;

public class Ui {
    public Ui() {
    }
    public void showWelcomeText() {
        System.out.println("Hello! I'm Chatteroo\n" + "What can I do for you?\n");
    }

    public void showByeText() {
        System.out.println("Chatteroo says bye and hopes to see you again soon!\n");
    }

    public void showNoTaskText() {
        System.out.println("There are no tasks in the list.\n");
    }
}
