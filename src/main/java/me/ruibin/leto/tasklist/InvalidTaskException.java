package me.ruibin.leto.tasklist;

import me.ruibin.leto.ui.Ui;

public class InvalidTaskException extends Exception {
    private final String message;

    public InvalidTaskException(String message) {
        this.message = message;
    }
    public void printException() {
        Ui.letoSpeak("We have a problem! " + this.message);
    }
}
