package Gluti.helpers;

/**
 * Represents the control hub for user input and filestorage for user
 */
public class Ui {

    /**
     * Initializes a Ui instance and sets the status to "working"
     */
    public Ui(){
    }

    public String typeHi() {
        return "Hello! I'm Gluti\n" +
                "What can I do for you?";
    }
    public String typeBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String sendMessage(String s) {
        return s;
    }
}
