package me.ruibin.leto.tasklist;

import me.ruibin.leto.ui.Ui;

public class InvalidTaskException extends Exception {

    public InvalidTaskException(String message) {
        super(message);
    }
    public void printException() {
        Ui.letoSpeak("We have a problem! " + this.getMessage());
    }
}
