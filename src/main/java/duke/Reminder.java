package duke;

import java.util.TimerTask;

/**
 * Represents a reminder that is triggered at a specific time to inform the user of a task.
 */
public class Reminder extends TimerTask {
    private String message;
    public Reminder(String message) {
        this.message = message;
    }
    @Override
    public void run() {
        Popup.showReminderPopup(message);
    }


}
