package dude.Utils;

/**
 * The Ui class handles the user interface of the application.
 */
public class Ui {


    /**
     * Displays the welcome message to system print stream.
     */
    public void showWelcome() {
        String msg = "\t-----------------------------------\n"
                + "\t\tHello! I'm dude.Dude\n"
                + "\t\tWhat can I do for you?\n"
                + "\t-----------------------------------\n";
        System.out.println(msg);
    }

    /**
     * Display a message to system print stream.
     *
     * @param msg The message to be displayed.
     */
    public void showMessage(String msg) {

        String temp = msg;

        temp = temp.replaceAll("\n", "\n\t\t");
        temp = "\t\t" + "\t-----------------------------------\n" + temp
                + "\n\t-----------------------------------";
        System.out.println(temp);
    }
}
