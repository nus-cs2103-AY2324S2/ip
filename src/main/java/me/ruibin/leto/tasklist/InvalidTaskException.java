package me.ruibin.leto.tasklist;

import me.ruibin.leto.ui.Ui;

/** Exception for invalid input for a Task */
public class InvalidTaskException extends Exception {

    public InvalidTaskException(String message) {
        super(message);
    }
    /** Print the error message according using Ui.letoSpeak. */
    public String printException() {
        return Ui.letoSpeak("We have a problem! " + this.getMessage());
    }
}
