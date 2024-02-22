package duke;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Represents a popup window to display reminders.
 */
public class Popup {
    /**
     * Displays a reminder popup using JavaFX with the given message.
     *
     * @param message Message to be displayed in the popup.
     */
    public static void showReminderPopup(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
